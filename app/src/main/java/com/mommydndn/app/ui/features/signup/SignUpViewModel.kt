package com.mommydndn.app.ui.features.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.mommydndn.app.data.api.model.response.Emd
import com.mommydndn.app.data.model.common.LocationSearchType
import com.mommydndn.app.data.model.location.LocationInfo
import com.mommydndn.app.data.model.user.SignUpInfo
import com.mommydndn.app.data.model.user.canSignUp
import com.mommydndn.app.domain.model.location.EmdItem
import com.mommydndn.app.domain.model.tos.TermsOfService
import com.mommydndn.app.domain.model.user.UserType
import com.mommydndn.app.domain.usecase.location.GetLocationsUseCase
import com.mommydndn.app.domain.usecase.location.GetNearestLocationsUseCase
import com.mommydndn.app.domain.usecase.tos.GetTermsOfServiceUseCase
import com.mommydndn.app.domain.usecase.tos.UpdateTermsOfServiceParams
import com.mommydndn.app.domain.usecase.tos.UpdateTermsOfServiceStatusUseCase
import com.mommydndn.app.domain.usecase.user.SaveTokenParams
import com.mommydndn.app.domain.usecase.user.SaveUserTokenUseCase
import com.mommydndn.app.domain.usecase.user.SignUpParams
import com.mommydndn.app.domain.usecase.user.SignUpUseCase
import com.mommydndn.app.util.result.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val updateTermsOfServiceStatusUseCase: UpdateTermsOfServiceStatusUseCase,
    private val signUpUseCase: SignUpUseCase,
    private val getTermsOfServiceUseCase: GetTermsOfServiceUseCase,
    private val saveUserTokenUseCase: SaveUserTokenUseCase,
    private val getNearestLocationsUseCase: GetNearestLocationsUseCase,
    private val getLocationsUseCase: GetLocationsUseCase
) : ViewModel() {

    private val currentLocationFlow = MutableStateFlow(LocationInfo(0.0, 0.0))
    private val keywordFlow = MutableStateFlow<String>("")

    private lateinit var signUpInfo: SignUpInfo

    private val _userTypeSelectUiState =
        MutableStateFlow<SignUpUiState.UserTypeSelect>(SignUpUiState.UserTypeSelect.Loading)
    val userTypeSelectUiState = _userTypeSelectUiState.stateIn(
        viewModelScope,
        SharingStarted.Eagerly,
        _userTypeSelectUiState.value
    )

    private val _locationSearchUiState =
        MutableStateFlow<SignUpUiState.LocationSearch>(SignUpUiState.LocationSearch.Loading)
    val locationSearchUiState = _locationSearchUiState
        .stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            _locationSearchUiState.value
        )

    val nearestLocations: Flow<PagingData<EmdItem>> = currentLocationFlow
        .flatMapLatest { currentLocation ->
            getNearestLocationsUseCase.invoke(currentLocation)
                .map { result ->
                    when (result) {
                        is Result.Success -> result.data
                        else -> PagingData.empty()
                    }
                }
        }

    val searchedLocations: Flow<PagingData<EmdItem>> = keywordFlow
        .flatMapLatest { keyword ->
            getLocationsUseCase.invoke(keyword)
                .map { result ->
                    when (result) {
                        is Result.Success -> result.data
                        else -> PagingData.empty()
                    }
                }
        }

    init {
        fetchTermsOfService()
        observeKeywordFlow()
    }

    private fun observeKeywordFlow() {
        viewModelScope.launch {
            keywordFlow.collectLatest { keyword ->
                _locationSearchUiState.update { state ->
                    state.keyword = keyword
                    state
                }
            }
        }
    }

    private fun fetchTermsOfService() {
        viewModelScope.launch {
            val result = getTermsOfServiceUseCase(Unit)

            _locationSearchUiState.update { state ->
                when (result) {
                    is Result.Success -> {
                        state.TOSList = result.data
                        state
                    }

                    else -> state
                }
            }
        }
    }

    fun signUp(
        list: List<TermsOfService>
    ) {
        if (!signUpInfo.canSignUp()) {
            // SignUpInfo 객체가 null 이거나 값들이 정상적으로 저장되어있지 않을때
            return // todo Return fail
        }

        viewModelScope.launch {
            when (
                val result = signUpUseCase.invoke(
                    SignUpParams(
                        accessToken = signUpInfo!!.accessToken!!,
                        oAuthType = signUpInfo.oAuthType!!,
                        userType = signUpInfo.userType!!,
                        emdId = signUpInfo.emdId!!
                    )
                )
            ) {
                is Result.Success -> {
                    saveUserToken(result.data.accessToken, result.data.refreshToken)
                    updateTermsOfService(list)
                    _locationSearchUiState.update { SignUpUiState.LocationSearch.SignUpSuccess }
                }

                is Result.Loading -> {
                    // TODO
                }

                is Result.Failure -> {
                    // TODO
                }
            }
        }
    }

    private fun saveUserToken(accessToken: String, refreshToken: String) {
        viewModelScope.launch {
            saveUserTokenUseCase(
                SaveTokenParams(
                    accessToken = accessToken,
                    refreshToken = refreshToken
                )
            )
        }
    }

    private fun updateTermsOfService(list: List<TermsOfService>) {
        viewModelScope.launch {
            updateTermsOfServiceStatusUseCase.invoke(UpdateTermsOfServiceParams(list))
        }
    }

    fun updateSignUpInfo(currentSignUpInfo: SignUpInfo?) {
        if (currentSignUpInfo != null) {
            signUpInfo = currentSignUpInfo
        }
    }

    fun updateEmdId(emdId: Int?) {
        signUpInfo = signUpInfo.copy(emdId = emdId)
    }

    fun updateUserType(userType: UserType?) {
        signUpInfo = signUpInfo.copy(userType = userType)
    }

    fun updateKeyword(keyword: String) {
        keywordFlow.value = keyword
        _locationSearchUiState.update { currentState ->
            currentState.locationSearchType = LocationSearchType.KEYWORD
            currentState
        }
    }


    fun updateLocationInfo(locationInfo: LocationInfo) {
        currentLocationFlow.value = locationInfo
        _locationSearchUiState.update { currentState ->
            currentState.locationSearchType = LocationSearchType.LOCATION
            currentState
        }
    }

    fun updateTermsApprovalStatus(id: Int, isChecked: Boolean) {
        _locationSearchUiState.update { currentState ->
            if (currentState is SignUpUiState.LocationSearch.Success) {
                currentState.TOSList.map { item ->
                    if (item.id == id) {
                        item.copy(isApproved = isChecked)
                    } else {
                        item
                    }
                }
            }
            currentState
        }
    }

    fun clearKeyword() {
        keywordFlow.value = ""
    }


}
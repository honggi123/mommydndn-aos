package com.mommydndn.app.ui.features.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
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
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

private data class SignUpViewModelState(
    val signUpStep: SignUpStep = SignUpStep.USER_TYPE,
    val locationSearchType: LocationSearchType = LocationSearchType.LOCATION,
    val keyword: String = "",
    val termsAndConditions: List<TermsOfService> = emptyList(),
    val signUpInfo: SignUpInfo? = null,
    val errorMessages: String = "",
    val isLoading: Boolean = false,
    val isSignUpSuccess: Boolean = false
) {
    fun toUiState(): SignUpUiState =
        if (signUpStep == SignUpStep.USER_TYPE) {
            SignUpUiState.UserTypeSelect(
                isLoading = isLoading,
                signUpInfo = signUpInfo,
                errorMessages = errorMessages
            )
        } else {
            SignUpUiState.LocationSearch(
                locationSearchType = locationSearchType,
                keyword = keyword,
                termsAndConditions = termsAndConditions,
                isLoading = isLoading,
                signUpInfo = signUpInfo,
                errorMessages = errorMessages,
                isSignUpSuccess = isSignUpSuccess
            )
        }
}

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

    private val viewModelState = MutableStateFlow(SignUpViewModelState())

    val uiState = viewModelState
        .map(SignUpViewModelState::toUiState)
        .stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            viewModelState.value.toUiState()
        )

    val searchedNearestLocations: Flow<PagingData<EmdItem>> = currentLocationFlow
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
                viewModelState.update {
                    it.copy(keyword = keyword)
                }
            }
        }
    }

    private fun fetchTermsOfService() {
        viewModelScope.launch {
            val result = getTermsOfServiceUseCase(Unit)

            viewModelState.update {
                when (result) {
                    is Result.Success -> it.copy(termsAndConditions = result.data)
                    else -> it
                }
            }
        }
    }

    fun signUp(
        signUpInfo: SignUpInfo?
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
                    updateTermsOfService()
                    setSignUpSuccessState()
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

    private fun updateTermsOfService() {
        viewModelScope.launch {
            updateTermsOfServiceStatusUseCase.invoke(UpdateTermsOfServiceParams(viewModelState.value.termsAndConditions))
        }
    }

    private fun setSignUpSuccessState() {
        viewModelState.update {
            it.copy(isSignUpSuccess = true)
        }
    }

    fun setSignUpInfo(currentSignUpInfo: SignUpInfo?) {
        viewModelState.update {
            it.copy(signUpInfo = currentSignUpInfo)
        }
    }

    fun setEmdId(emdId: Int?) {
        val currentSignUpInfo = viewModelState.value.signUpInfo
        if (currentSignUpInfo != null) {
            viewModelState.update {
                it.copy(signUpInfo = currentSignUpInfo.copy(emdId = emdId))
            }
        }
    }

    fun setUserType(userType: UserType?) {
        viewModelState.update {
            val currentSignUpInfo = it.signUpInfo?.copy(userType = userType)
            it.copy(signUpInfo = currentSignUpInfo, signUpStep = SignUpStep.SEARCH_LOCATION)
        }
    }

    fun setKeyword(keyword: String) {
        keywordFlow.value = keyword
        viewModelState.update {
            it.copy(keyword = keyword, locationSearchType = LocationSearchType.KEYWORD)
        }
    }

    fun clearKeyword() {
        keywordFlow.value = ""
    }

    fun setLocationInfo(locationInfo: LocationInfo) {
        currentLocationFlow.value = locationInfo
        viewModelState.update {
            it.copy(locationSearchType = LocationSearchType.LOCATION)
        }
    }

    fun setTermsCheckStatus(id: Int, isChecked: Boolean) {
        viewModelState.update { currentState ->
            currentState.copy(
                termsAndConditions = currentState.termsAndConditions.map { item ->
                    if (item.id == id) {
                        item.copy(isApproved = isChecked)
                    } else {
                        item
                    }
                }
            )
        }
    }

    fun setSignUpStep(signUpStep: SignUpStep) {
        viewModelState.update {
            it.copy(signUpStep = signUpStep)
        }
    }
}

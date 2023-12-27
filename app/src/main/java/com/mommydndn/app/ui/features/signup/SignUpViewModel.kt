package com.mommydndn.app.ui.features.signup

import android.Manifest
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.mommydndn.app.domain.model.location.CoordinatesInfo
import com.mommydndn.app.domain.model.user.SignUpInfo
import com.mommydndn.app.domain.model.location.LocationInfo
import com.mommydndn.app.domain.model.tos.TosAgreementStatus
import com.mommydndn.app.domain.model.tos.TermsOfService
import com.mommydndn.app.domain.model.user.UserType
import com.mommydndn.app.domain.usecase.location.GetLocationsUseCase
import com.mommydndn.app.domain.usecase.location.GetNearestLocationsUseCase
import com.mommydndn.app.domain.usecase.tos.GetTermsOfServiceUseCase
import com.mommydndn.app.domain.usecase.tos.UpdateTosParams
import com.mommydndn.app.domain.usecase.tos.UpdateTosStatusUseCase
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

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val updateTosStatusUseCase: UpdateTosStatusUseCase,
    private val signUpUseCase: SignUpUseCase,
    private val getTermsOfServiceUseCase: GetTermsOfServiceUseCase,
    private val saveUserTokenUseCase: SaveUserTokenUseCase,
    private val getNearestLocationsUseCase: GetNearestLocationsUseCase,
    private val getLocationsUseCase: GetLocationsUseCase
) : ViewModel() {

    val permissions = arrayOf(
        Manifest.permission.ACCESS_COARSE_LOCATION,
        Manifest.permission.ACCESS_FINE_LOCATION
    )

    private val searchManager = SearchManager()

    private lateinit var signUpInfo: SignUpInfo

    private val _userTypeSelectUiState =
        MutableStateFlow<SignUpUiState.UserTypeSelect>(SignUpUiState.UserTypeSelect.Loading)
    val userTypeSelectUiState = _userTypeSelectUiState.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5_000),
        _userTypeSelectUiState.value
    )

    private val _locationSearchUiState =
        MutableStateFlow<SignUpUiState.LocationSearch>(SignUpUiState.LocationSearch.Loading)
    val locationSearchUiState = _locationSearchUiState
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5_000),
            _locationSearchUiState.value
        )

    val nearbyLocations: Flow<PagingData<LocationInfo>> = searchManager.currentLocationFlow
        .flatMapLatest { currentLocation -> getNearestLocationsUseCase.invoke(currentLocation) }

    val searchedLocations: Flow<PagingData<LocationInfo>> = searchManager.keywordFlow
        .flatMapLatest { keyword -> getLocationsUseCase.invoke(keyword) }

    init {
        fetchTermsOfService()
    }

    private fun fetchTermsOfService() {
        viewModelScope.launch {
            val result = getTermsOfServiceUseCase(Unit)

            _locationSearchUiState.update { state ->
                when (result) {
                    is Result.Success -> {
                        SignUpUiState.LocationSearch.Success(tosList = result.data)
                    }

                    else -> state
                }
            }
        }
    }

    fun signUp(
        approvedTermsList: List<TermsOfService>,
        allTermsList: List<TermsOfService>
    ) {
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
                    updateCheckedTermsOfService(approvedTermsList, allTermsList)
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

    private fun updateCheckedTermsOfService(
        approvedTermsList: List<TermsOfService>,
        allTermsList: List<TermsOfService>
    ) {

        val statusList = allTermsList.map { term ->
            val isApproved = approvedTermsList.any { approvedTerm -> approvedTerm.id == term.id }
            TosAgreementStatus(term.id, isApproved)
        }

        viewModelScope.launch {
            updateTosStatusUseCase.invoke(UpdateTosParams(statusList))
        }
    }

    fun updateSignUpInfo(currentSignUpInfo: SignUpInfo?) {
        if (currentSignUpInfo != null) {
            signUpInfo = currentSignUpInfo
        }
    }

    fun updateMyLocation(locationInfo: LocationInfo?) {
        signUpInfo = signUpInfo.copy(emdId = locationInfo?.id)
    }

    fun updateUserType(userType: UserType?) {
        signUpInfo = signUpInfo.copy(userType = userType)
    }

    fun search(keyword: String) {
        searchManager.updateKeyword(keyword = keyword)
    }

    fun searchNearby(coordinatesInfo: CoordinatesInfo) {
        searchManager.updateMyLocation(coordinatesInfo = coordinatesInfo)
    }

    fun clearKeyword() {
        searchManager.clearKeyword()
    }

}


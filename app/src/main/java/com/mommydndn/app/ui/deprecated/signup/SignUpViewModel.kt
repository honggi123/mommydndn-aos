package com.mommydndn.app.ui.deprecated.signup

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.google.android.gms.location.FusedLocationProviderClient
import com.mommydndn.app.domain.model.location.CoordinatesInfo
import com.mommydndn.app.domain.model.location.Neighborhood
import com.mommydndn.app.domain.model.tos.TermsOfService
import com.mommydndn.app.domain.model.tos.TosAgreementStatus
import com.mommydndn.app.domain.usecase.location.SearchNeighborhoodByCoordinatesUseCase
import com.mommydndn.app.domain.usecase.location.SearchNeighborhoodByQueryUseCase
import com.mommydndn.app.domain.usecase.tos.GetTermsOfServiceUseCase
import com.mommydndn.app.domain.usecase.user.SaveTokenParams
import com.mommydndn.app.domain.usecase.user.SaveUserTokenUseCase
import com.mommydndn.app.domain.usecase.user.SignUpUseCase
import com.mommydndn.app.utils.result.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

// @HiltViewModel
class SignUpViewModel constructor(
    // private val agreeToTermsOfServiceUseCase: AgreeToTermsOfServiceUseCase,
    private val signUpUseCase: SignUpUseCase,
    private val getTermsOfServiceUseCase: GetTermsOfServiceUseCase,
    private val saveUserTokenUseCase: SaveUserTokenUseCase,
    private val searchNeighborhoodByCoordinatesUseCase: SearchNeighborhoodByCoordinatesUseCase,
    private val searchNeighborhoodByQueryUseCase: SearchNeighborhoodByQueryUseCase
) : ViewModel() {

    private val searchManager = SearchManager()

    // private var signUpInfo = SignUpInfo()

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

    /*
    searchManager.currentLocationFlow
        .flatMapLatest { currentLocation -> searchNeighborhoodByCoordinates.invoke(currentLocation) }
     */
    val nearbyLocations: Flow<PagingData<Neighborhood>> = TODO()


    val searchedLocations: Flow<PagingData<Neighborhood>> = TODO()
    /*
    searchManager.keywordFlow
        .flatMapLatest { keyword -> searchNeighborhoodByQueryUseCase.invoke(keyword) }
     */


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
                    TODO()
                    /*
                    SignUpParams(
                accessToken = signUpInfo!!.accessToken!!,
                oAuthType = signUpInfo.oAuthType!!,
                userType = signUpInfo.userType!!,
                emdId = signUpInfo.emdId!!
            )
                     */
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
            // agreeToTermsOfServiceUseCase.invoke(UpdateTosParams(statusList))
        }
    }

    /*
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
     */

    fun search(keyword: String) {
        searchManager.updateKeyword(keyword = keyword)
    }

    fun searchNearby(coordinatesInfo: CoordinatesInfo) {
        searchManager.updateMyLocation(coordinatesInfo = coordinatesInfo)
    }

    fun clearKeyword() {
        searchManager.clearKeyword()
    }

    fun getCurrentLocation(
        fusedLocationClient: FusedLocationProviderClient,
        locationCallback: (Double, Double) -> Unit,
    ) {
        try {
            fusedLocationClient.lastLocation.addOnSuccessListener {
                it?.let { locationCallback(it.latitude, it.longitude) }
            }
        } catch (e: SecurityException) {
            Log.d("SignUpViewModel", e.stackTraceToString())
        }
    }

}


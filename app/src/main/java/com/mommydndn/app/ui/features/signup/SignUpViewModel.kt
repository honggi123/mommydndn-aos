package com.mommydndn.app.ui.features.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.mommydndn.app.data.model.common.LocationSearchType
import com.mommydndn.app.data.model.location.EmdItem
import com.mommydndn.app.data.model.location.LocationInfo
import com.mommydndn.app.data.model.TermsAndConditions.TermsAndConditionsItem
import com.mommydndn.app.data.model.user.SignUpInfo
import com.mommydndn.app.data.model.user.shouldSkipSignUp
import com.mommydndn.app.domain.model.user.UserType
import com.mommydndn.app.domain.usecase.location.GetLocationsUseCase
import com.mommydndn.app.domain.usecase.location.GetNearestLocationsUseCase
import com.mommydndn.app.domain.usecase.termsAndConditions.GetAllTermsAndConditionsUseCase
import com.mommydndn.app.domain.usecase.termsAndConditions.UpdateTermsParams
import com.mommydndn.app.domain.usecase.termsAndConditions.UpdateTermsUseCase
import com.mommydndn.app.domain.usecase.user.SaveTokenParams
import com.mommydndn.app.domain.usecase.user.SaveUserTokenUseCase
import com.mommydndn.app.domain.usecase.user.SignUpParams
import com.mommydndn.app.domain.usecase.user.SignUpUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.mommydndn.app.util.result.Result
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update


private data class SignUpViewModelState(
    val signUpStep: SignUpStep = SignUpStep.USER_TYPE,
    val locationSearchType: LocationSearchType = LocationSearchType.LOCATION,
    val keyword: String = "",
    val termsAndCondtions: List<TermsAndConditionsItem> = emptyList(),
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
                termsAndCondtions = termsAndCondtions,
                isLoading = isLoading,
                signUpInfo = signUpInfo,
                errorMessages = errorMessages,
                isSignUpSuccess = isSignUpSuccess
            )
        }
}

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val updateTermsUseCase: UpdateTermsUseCase,
    private val signUpUseCase: SignUpUseCase,
    private val getAllTermsAndConditionsUseCase: GetAllTermsAndConditionsUseCase,
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

    val searchedNearest: Flow<PagingData<EmdItem>> = currentLocationFlow
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
        fetchAllTermsAndConditions()
        observeKeywordFlow()
    }

    private fun observeKeywordFlow(){
        viewModelScope.launch {
            keywordFlow.collectLatest { keyword ->
                viewModelState.update {
                    it.copy(keyword = keyword)
                }
            }
        }
    }

    private fun fetchAllTermsAndConditions() {
        viewModelScope.launch {
            getAllTermsAndConditionsUseCase(Unit).collectLatest { result ->
                viewModelState.update {
                    when (result) {
                        is Result.Success -> it.copy(termsAndCondtions = result.data)
                        else -> it
                    }
                }
            }
        }
    }

    fun signUp(
        signUpInfo: SignUpInfo?
    ) {
        if (signUpInfo.shouldSkipSignUp()) {
            return
        }

        viewModelScope.launch {
            when (val result = signUpUseCase.invoke(
                SignUpParams(
                    accessToken = signUpInfo!!.accessToken!!,
                    oAuthType = signUpInfo.oAuthType!!,
                    userType = signUpInfo.userType!!,
                    emdId = signUpInfo.emdId!!
                )
            )) {
                is Result.Success -> {
                    saveUserToken(result.data.accessToken, result.data.refreshToken)
                    updateTerms()
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

    private fun updateTerms() {
        viewModelScope.launch {
            updateTermsUseCase.invoke(UpdateTermsParams(viewModelState.value.termsAndCondtions))
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
            it.copy(signUpInfo = currentSignUpInfo)
        }
    }


    fun setKeyword(keyword: String) {
        viewModelState.update {
            it.copy(keyword = keyword, locationSearchType = LocationSearchType.KEYWORD)
        }
    }

    fun clearKeyword() {
        viewModelState.update {
            it.copy(keyword = "")
        }
    }

    fun setLocationInfo(locationInfo: LocationInfo) {
        viewModelState.update {
            it.copy(locationSearchType = LocationSearchType.LOCATION)
        }
        currentLocationFlow.value = locationInfo
    }

    fun setTermsCheckStatus(termsId: Int, isChecked: Boolean) {
        viewModelState.update { currentState ->
            currentState.copy(
                termsAndCondtions = currentState.termsAndCondtions.map { term ->
                    if (term.termsId == termsId) {
                        term.copy(isSelected = isChecked)
                    } else {
                        term
                    }
                }
            )
        }
    }

}



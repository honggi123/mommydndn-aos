package com.mommydndn.app.ui.features.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.mommydndn.app.data.api.model.response.Emd
import com.mommydndn.app.data.model.common.LocationSearchType
import com.mommydndn.app.data.model.map.EmdItem
import com.mommydndn.app.data.model.map.LocationInfo
import com.mommydndn.app.data.model.user.SignUpInfo
import com.mommydndn.app.data.model.terms.TermsItem
import com.mommydndn.app.data.model.user.shouldSkipSignUp
import com.mommydndn.app.domain.model.user.UserType
import com.mommydndn.app.domain.repository.AccountRepository
import com.mommydndn.app.domain.repository.LocationRepository
import com.mommydndn.app.domain.repository.TermsAndConditionsRepository
import com.mommydndn.app.domain.usecase.terms.GetAllTermsUseCase
import com.mommydndn.app.domain.usecase.terms.UpdateTermsParams
import com.mommydndn.app.domain.usecase.terms.UpdateTermsUseCase
import com.mommydndn.app.domain.usecase.user.SignUpParams
import com.mommydndn.app.domain.usecase.user.SignUpUseCase
import com.skydoves.sandwich.onSuccess
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.mommydndn.app.util.result.Result
import kotlinx.coroutines.flow.asStateFlow

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val updateTermsUseCase: UpdateTermsUseCase,
    private val signUpUseCase: SignUpUseCase,
    private val getAllTermsUseCase: GetAllTermsUseCase,
    private val locationRepository: LocationRepository
) : ViewModel() {

    private val _uiState: MutableStateFlow<SignUpUiState> = MutableStateFlow(SignUpUiState.Loading)
    val uiState: StateFlow<SignUpUiState> = _uiState.asStateFlow()

    private val _signUpInfo = MutableStateFlow(SignUpInfo())
    val signUpInfo: StateFlow<SignUpInfo> = _signUpInfo

    private val _searchType = MutableStateFlow<LocationSearchType>(LocationSearchType.LOCATION)
    val searchType: StateFlow<LocationSearchType> = _searchType

    private val _keyword = MutableStateFlow<String>("")
    val keyword: StateFlow<String> = _keyword

    private val _terms = MutableStateFlow<List<TermsItem>>(emptyList())
    val terms: StateFlow<List<TermsItem>> = _terms

    private val _currentLocation = MutableStateFlow(LocationInfo(0.0, 0.0))

    private val searchedLocationsByKeyword: Flow<PagingData<EmdItem>> = _keyword
        .debounce(200)
        .distinctUntilChanged()
        .flatMapLatest {
            locationRepository.fetchLocationsByKeyword(it)
        }.cachedIn(viewModelScope)

    private val searchedNearestByLocation: Flow<PagingData<EmdItem>> = _currentLocation
        .flatMapLatest {
            locationRepository.fetchNearestByLocation(it)
        }.cachedIn(viewModelScope)

    val searchedLocations: Flow<PagingData<EmdItem>> =
        combine(
            searchedLocationsByKeyword,
            searchedNearestByLocation
        ) { locationsKeyword, locationsNearest ->
            if (searchType.value == LocationSearchType.KEYWORD) locationsKeyword else locationsNearest
        }

    init {
        fetchAllTerms()
    }

    private fun fetchAllTerms() {
        viewModelScope.launch {
            getAllTermsUseCase(Unit).collectLatest { result ->
                if (result is Result.Success) {
                    _terms.value = result.data
                }
            }
        }
    }

    private fun updateTermsCheckedStatus(termsItem: List<TermsItem>) {
        viewModelScope.launch {
            updateTermsUseCase.invoke(UpdateTermsParams(termsItem))
        }
    }

    fun signUp(
        signUpInfo: SignUpInfo
    ) {
        if (signUpInfo.shouldSkipSignUp()) {
            return
        }

        viewModelScope.launch {
            signUpUseCase.invoke(
                SignUpParams(
                    accessToken = signUpInfo.accessToken!!,
                    oAuthType = signUpInfo.oAuthType!!,
                    userType = signUpInfo.userType!!,
                    emdId = signUpInfo.emdId!!
                )
            ).let { result ->
                if (result is Result.Success) {
                    updateTermsCheckedStatus(terms.value)
                }
            }
        }
    }

    fun setSignUpInfo(currentSignUpInfo: SignUpInfo?) {
        if (currentSignUpInfo != null) {
            _signUpInfo.value = currentSignUpInfo
        }
    }

    fun setEmdId(emdId: Int?) {
        val currentSignUpInfo = signUpInfo.value
        _signUpInfo.value = currentSignUpInfo.copy(emdId = emdId)
    }

    fun setUserType(userType: UserType?) {
        val currentSignUpInfo = signUpInfo.value
        _signUpInfo.value = currentSignUpInfo.copy(userType = userType)

        _uiState.value = SignUpUiState.UserTypeSelected
    }


    fun setKeyword(keyword: String) {
        _keyword.value = keyword
        _searchType.value = LocationSearchType.KEYWORD
    }

    fun clearKeyword() {
        _keyword.value = ""
    }

    fun setLocationInfo(locationInfo: LocationInfo) {
        _currentLocation.value = locationInfo
        _searchType.value = LocationSearchType.LOCATION
    }

    fun setTermsCheckStatus(termsId: Int, isChecked: Boolean) {
        val currentTermsList = terms.value.toMutableList()
        val index = currentTermsList.indexOfFirst { it.termsId == termsId }

        if (index != -1) {
            currentTermsList[index] = currentTermsList[index].copy(isSelected = isChecked)
            _terms.value = currentTermsList
        }
    }
}



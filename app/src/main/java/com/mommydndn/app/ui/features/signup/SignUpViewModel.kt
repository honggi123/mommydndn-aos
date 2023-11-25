package com.mommydndn.app.ui.features.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.mommydndn.app.data.model.map.EmdItem
import com.mommydndn.app.data.model.map.LocationInfo
import com.mommydndn.app.data.model.common.TownSearchType
import com.mommydndn.app.data.model.user.SignUpInfo
import com.mommydndn.app.data.model.terms.TermsItem
import com.mommydndn.app.domain.model.user.UserType
import com.mommydndn.app.domain.repository.AccountRepository
import com.mommydndn.app.domain.repository.LocationRepository
import com.mommydndn.app.domain.repository.TermsAndConditionsRepository
import com.skydoves.sandwich.onSuccess
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val accountRepository: AccountRepository,
    private val termsAndConditionsRepository: TermsAndConditionsRepository,
    private val locationRepository: LocationRepository
) : ViewModel() {

    private val _signUpInfo = MutableStateFlow(SignUpInfo())
    val signUpInfo: StateFlow<SignUpInfo> = _signUpInfo

    private val _searchType = MutableStateFlow<TownSearchType>(TownSearchType.LOCATION)
    val searchType: StateFlow<TownSearchType> = _searchType

    private val _keyword = MutableStateFlow<String>("")
    val keyword: StateFlow<String> = _keyword

    private val _terms = MutableStateFlow<List<TermsItem>>(emptyList())
    val terms: StateFlow<List<TermsItem>> = _terms

    private val _currentLocation = MutableStateFlow(LocationInfo(0.0, 0.0))

    val searchedTownsFlow: Flow<PagingData<EmdItem>> = _keyword
        .debounce(200)
        .distinctUntilChanged()
        .flatMapLatest {
            locationRepository.fetchLocationsByKeyword(it)
        }.cachedIn(viewModelScope)

    val searchedTownsByLocation: Flow<PagingData<EmdItem>> = _currentLocation
        .flatMapLatest {
            locationRepository.fetchNearestByLocation(it)
        }.cachedIn(viewModelScope)

    init {
        updateTerms()
    }

    private fun updateTerms() {
        viewModelScope.launch {
            termsAndConditionsRepository.fetchAllTerms()
                .collectLatest { _terms.value = it }
        }
    }

    fun updateTermsCheckedStatus(termsItem: List<TermsItem>) {
        viewModelScope.launch {
            termsAndConditionsRepository.updateTermsCheckedStatus(termsItem)
        }
    }

    fun signUp(
        signUpInfo: SignUpInfo
    ) {
        viewModelScope.launch {
            accountRepository.signUp(signUpInfo)
                .onSuccess {
                    updateTermsCheckedStatus(_terms.value)
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
    }


    fun setKeyword(keyword: String) {
        _keyword.value = keyword
        _searchType.value = TownSearchType.KEYWORD
    }

    fun clearKeyword() {
        _keyword.value = ""
    }

    fun setLocationInfo(locationInfo: LocationInfo) {
        _currentLocation.value = locationInfo
        _searchType.value = TownSearchType.LOCATION
    }

    fun setTermItemCheckedState(termsId: Int, isChecked: Boolean) {
        val currentTermsList = terms.value.toMutableList()
        val index = currentTermsList.indexOfFirst { it.termsId == termsId }

        if (index != -1) {
            currentTermsList[index] = currentTermsList[index].copy(isSelected = isChecked)
            _terms.value = currentTermsList
        }
    }
}
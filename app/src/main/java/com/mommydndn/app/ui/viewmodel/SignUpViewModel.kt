package com.mommydndn.app.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.mommydndn.app.data.model.TermsItem
import com.mommydndn.app.data.model.EmdItem
import com.mommydndn.app.data.model.LocationInfo
import com.mommydndn.app.data.model.NearestSearchType
import com.mommydndn.app.data.model.OAuthType
import com.mommydndn.app.data.model.SignUpInfo
import com.mommydndn.app.data.model.UserType
import com.mommydndn.app.data.respository.AccountRepository
import com.mommydndn.app.data.respository.LocationRepository
import com.mommydndn.app.data.respository.TermsRepository
import com.skydoves.sandwich.getOrElse
import com.skydoves.sandwich.getOrThrow
import com.skydoves.sandwich.message
import com.skydoves.sandwich.onError
import com.skydoves.sandwich.onFailure
import com.skydoves.sandwich.onSuccess
import com.skydoves.sandwich.suspendOnSuccess
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val accountRepository: AccountRepository,
    private val termsRepository: TermsRepository,
    private val locationRepository: LocationRepository
) : ViewModel() {

    private val _signUpInfo = MutableStateFlow<SignUpInfo>(SignUpInfo())
    val signUpInfo: StateFlow<SignUpInfo> = _signUpInfo

    private val _terms = MutableStateFlow<List<TermsItem>>(listOf())
    val terms: StateFlow<List<TermsItem>> = _terms

    private val _searchType = MutableStateFlow<NearestSearchType>(NearestSearchType.LOCATION)
    val searchType: StateFlow<NearestSearchType> = _searchType

    private val _keyword = MutableStateFlow<String>("")
    val keyword: StateFlow<String> = _keyword

    private val _location = MutableStateFlow<LocationInfo>(LocationInfo(0.0, 0.0))

    private val _searchedTownsFlow: Flow<PagingData<EmdItem>> = _keyword
        .debounce(200)
        .distinctUntilChanged()
        .flatMapLatest {
            locationRepository.fetchNearestByKeyword(it)
        }.cachedIn(viewModelScope)
    val searchedTownsFlow: Flow<PagingData<EmdItem>> = _searchedTownsFlow

    private val _searchedTownsByLocation: Flow<PagingData<EmdItem>> = _location
        .flatMapLatest {
            locationRepository.fetchNearestByLocation(it)
        }.cachedIn(viewModelScope)
    val searchedTownsByLocation: Flow<PagingData<EmdItem>> = _searchedTownsByLocation

    init {
        viewModelScope.launch {
            updateTerms()
        }
    }


    private suspend fun updateTerms() {
        val res = termsRepository.fetchAllTerms()
        _terms.value = res.getOrElse { emptyList() }
    }

    fun signUp(
        signUpInfo: SignUpInfo
    ) {
        viewModelScope.launch {
            accountRepository.signUp(signUpInfo)
        }
    }

    fun updateSignUpInfo(currentSignUpInfo: SignUpInfo?) {
        if (currentSignUpInfo != null) {
            _signUpInfo.value = currentSignUpInfo
        }
    }

    fun updateEmdId(emdId: Int?) {
        val currentSignUpInfo = signUpInfo.value
        _signUpInfo.value = currentSignUpInfo.copy(emdId = emdId)
    }

    fun updateUserType(userType: UserType?) {
        val currentSignUpInfo = signUpInfo.value
        _signUpInfo.value = currentSignUpInfo.copy(userType = userType)
    }


    fun updateKeyword(keyword: String) {
        _keyword.value = keyword
        _searchType.value = NearestSearchType.KEYWORD
    }

    fun updateLocation(locationInfo: LocationInfo) {
        _location.value = locationInfo
        _searchType.value = NearestSearchType.LOCATION
    }
}
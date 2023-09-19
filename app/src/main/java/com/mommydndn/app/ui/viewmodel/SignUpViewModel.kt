package com.mommydndn.app.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.mommydndn.app.data.model.TermsItem
import com.mommydndn.app.data.model.EmdItem
import com.mommydndn.app.data.model.OAuthType
import com.mommydndn.app.data.model.SignUpInfo
import com.mommydndn.app.data.model.UserType
import com.mommydndn.app.data.respository.AccountRepository
import com.mommydndn.app.data.respository.LocationRepository
import com.mommydndn.app.data.respository.TermsRepository
import com.skydoves.sandwich.getOrElse
import com.skydoves.sandwich.getOrThrow
import com.skydoves.sandwich.onFailure
import com.skydoves.sandwich.onSuccess
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val accountRepository: AccountRepository,
    private val termsRepository: TermsRepository,
    private val locationRepository: LocationRepository
) : ViewModel() {

    private val _nearTowns = MutableStateFlow<List<EmdItem>>(listOf())
    val nearTowns: StateFlow<List<EmdItem>> = _nearTowns

    private val _isModalVisible = MutableStateFlow(false)
    val isModalVisible: StateFlow<Boolean> = _isModalVisible

    private val _terms = MutableStateFlow<List<TermsItem>>(listOf())
    val terms: StateFlow<List<TermsItem>> = _terms

    private val _keyword = MutableStateFlow<String>("")
    val keyword: StateFlow<String> = _keyword

    private val _signUpInfo = MutableStateFlow<SignUpInfo>(SignUpInfo())
    val signUpInfo: StateFlow<SignUpInfo> = _signUpInfo

    init {
        fetchAllTerms()
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


    private fun fetchAllTerms() {
        viewModelScope.launch {
            val res = termsRepository.fetchAllTerms()
            _terms.value = res.getOrElse { emptyList() }
        }
    }

    fun updateKeyword(keyword: String) {
        viewModelScope.launch {
            _keyword.value = keyword
        }
    }

    fun searchByLoaction(latitude: Double, longitude: Double) {
        viewModelScope.launch {
            locationRepository.fetchNearestByLocation(
                latitude = latitude,
                longitude = longitude
            ).onSuccess {
                _nearTowns.value = this.data.emdList
            }
        }
    }

    fun searchByKeyword(keyword: String) {
        viewModelScope.launch {
            locationRepository.fetchNearestByKeyword(
                keyword = keyword
            ).onSuccess {
                _nearTowns.value = this.data.emdList
            }
        }
    }

    fun showModal() {
        _isModalVisible.value = true
    }

    fun hideModal() {
        _isModalVisible.value = false
    }
}
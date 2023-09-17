package com.mommydndn.app.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.mommydndn.app.data.dto.TermsItem
import com.mommydndn.app.data.model.EmdItem
import com.mommydndn.app.data.model.LoginResponse
import com.mommydndn.app.data.model.LoginType
import com.mommydndn.app.data.respository.AccountRepository
import com.mommydndn.app.data.respository.LocationRepository
import com.mommydndn.app.data.respository.TermsRepository
import com.mommydndn.app.ui.TypeChoiceNav
import com.mommydndn.app.utils.NavigationUtils
import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.StatusCode
import com.skydoves.sandwich.getOrElse
import com.skydoves.sandwich.getOrThrow
import com.skydoves.sandwich.message
import com.skydoves.sandwich.onError
import com.skydoves.sandwich.onException
import com.skydoves.sandwich.onSuccess
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val termsRepository: TermsRepository,
    private val locationRepository: LocationRepository
) : ViewModel() {

    private val _nearTowns = MutableStateFlow<List<EmdItem>>(listOf())
    val nearTowns: StateFlow<List<EmdItem>> = _nearTowns

    private val _isModalVisible = MutableStateFlow(false)
    val isModalVisible: StateFlow<Boolean> = _isModalVisible

    private val _terms = MutableStateFlow<List<TermsItem>>(listOf())
    val terms: StateFlow<List<TermsItem>> = _terms

    init {
        fetchAllTerms()
    }

    private fun fetchAllTerms() {
        viewModelScope.launch {
            val res = termsRepository.fetchAllTerms()
            _terms.value = res.getOrElse { emptyList() }
        }
    }

    fun fetchNearTowns(latitude: Double, longitude: Double) {
        viewModelScope.launch {
            val res = locationRepository.fetchNearest(latitude = latitude, longitude = longitude)
            _nearTowns.value = res.getOrThrow().emdList
        }
    }

    fun showModal() {
        _isModalVisible.value = true
    }

    fun hideModal() {
        _isModalVisible.value = false
    }
}
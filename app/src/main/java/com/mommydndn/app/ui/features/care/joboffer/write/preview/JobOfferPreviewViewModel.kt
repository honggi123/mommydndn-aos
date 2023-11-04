package com.mommydndn.app.ui.features.care.joboffer.write.preview

import android.util.Log
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mommydndn.app.data.api.model.response.JobOfferResponse
import com.mommydndn.app.data.api.model.response.UserResponse
import com.mommydndn.app.data.model.map.LocationInfo
import com.mommydndn.app.data.respository.CaringRepository
import com.mommydndn.app.data.respository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class JobOfferPreviewViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val caringRepository: CaringRepository,
    private val userRepository: UserRepository
) : ViewModel() {

    private val _jobOffer = MutableStateFlow<JobOfferResponse?>(null)
    val jobOffer: StateFlow<JobOfferResponse?> = _jobOffer

    private val _loactionInfo = MutableStateFlow<LocationInfo?>(null)
    val loactionInfo: StateFlow<LocationInfo?> = _loactionInfo

    val authorInfo: StateFlow<UserResponse?> = userRepository.fetchUserInfo().stateIn(
        scope = viewModelScope,
        started = SharingStarted.Lazily,
        initialValue = null
    )

    fun updatePostInfo(jobofferId: Int) {
        viewModelScope.launch {
            caringRepository.fetchJobOffer(jobofferId).collectLatest {
                _jobOffer.value = it
//                updateAddress()
            }
        }
    }

//    private fun updateAddress() {
//        val latitude = _jobOffer.value.latitude
//        val longitude = _jobOffer.value.longitude
//
//
//        _loactionInfo.value = LocationInfo(latitude = latitude, longitude = longitude)
//    }

    companion object {
        private const val JOB_OFFER_POST_ID = "jobOfferId"
    }
}
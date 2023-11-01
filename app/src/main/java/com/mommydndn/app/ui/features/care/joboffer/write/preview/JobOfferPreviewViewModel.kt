package com.mommydndn.app.ui.features.care.joboffer.write.preview

import androidx.compose.runtime.collectAsState
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mommydndn.app.data.api.model.response.JobOfferResponse
import com.mommydndn.app.data.respository.CaringRepository
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
    private val caringRepository: CaringRepository
) : ViewModel() {

    private val _jobOffer = MutableStateFlow<JobOfferResponse?>(null)
    val jobOffer: StateFlow<JobOfferResponse?> = _jobOffer
    fun updatePost(jobofferId: Int?) {
        viewModelScope.launch {
            if (jobofferId != null) {
                caringRepository.fetchJobOffer(jobofferId).collectLatest {
                    _jobOffer.value = it
                }
            }
        }
    }

    companion object {
        private const val JOB_OFFER_POST_ID = "jobOfferId"
    }
}
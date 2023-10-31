package com.mommydndn.app.ui.features.care.joboffer.write.preview

import androidx.compose.runtime.collectAsState
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mommydndn.app.data.respository.CaringRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class JobOfferPreviewViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val caringRepository: CaringRepository
) : ViewModel() {

    val jobOffer = caringRepository.fetchJobOffer(1).stateIn(
        viewModelScope,
        started = SharingStarted.Lazily,
        initialValue = null
    )

    companion object {
        private const val JOB_OFFER_POST_ID = "jobOfferId"
    }
}
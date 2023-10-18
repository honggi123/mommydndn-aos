package com.mommydndn.app.ui.feature.care

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.mommydndn.app.data.model.care.JobOfferSummary
import com.mommydndn.app.data.respository.CaringRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


@HiltViewModel
class CareViewModel @Inject constructor(
    private val caringRepository: CaringRepository
) : ViewModel() {
    val searchedJobOfferSummary: Flow<PagingData<JobOfferSummary>> =
        caringRepository.fetchJobOfferSummary().cachedIn(viewModelScope)

}
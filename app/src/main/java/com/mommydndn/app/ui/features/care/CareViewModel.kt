package com.mommydndn.app.ui.features.care

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.mommydndn.app.data.model.care.FilterItem
import com.mommydndn.app.data.model.care.FilterType
import com.mommydndn.app.data.model.care.JobOfferSummary
import com.mommydndn.app.data.respository.CaringRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject


@HiltViewModel
class CareViewModel @Inject constructor(
    private val caringRepository: CaringRepository
) : ViewModel() {

    private val _filterItems = MutableStateFlow(
        listOf(
            FilterItem(FilterType.SORTING, "최신순", true),
            FilterItem(FilterType.CARING, "돌봄종류", false),
            FilterItem(FilterType.NEIGHBORHOODSCOPE, "동네범위", false),
            FilterItem(FilterType.PERIOD, "1회성/정기", false),
            FilterItem(FilterType.TIME, "시간", false)
        )
    )
    val filterItems: StateFlow<List<FilterItem>> = _filterItems

//    val searchedJobOfferSummary: Flow<PagingData<JobOfferSummary>> =
//        caringRepository.fetchJobOfferSummary().cachedIn(viewModelScope)

}
package com.mommydndn.app.ui.features.care

import androidx.lifecycle.ViewModel
import com.mommydndn.app.data.model.care.CaringType
import com.mommydndn.app.data.model.care.Filter.FilterItemsType
import com.mommydndn.app.data.model.care.Filter.FilterType
import com.mommydndn.app.data.model.care.SortingType
import com.mommydndn.app.data.respository.CaringRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.time.LocalTime
import javax.inject.Inject


@HiltViewModel
class CareViewModel @Inject constructor(
    private val caringRepository: CaringRepository
) : ViewModel() {

    private val _filterItems = MutableStateFlow(
        listOf(
            FilterType.Sorting(
                displayingName = "최신순",
                itemsType = FilterItemsType.Sorting(
                    list = listOf(
                        SortingType.LATEST,
                        SortingType.MOST_VIEW,
                        SortingType.HIGHEST_SALARY,
                        SortingType.CLOSEST
                    )
                ),
                isSelected = false
            ),

            FilterType.Caring(
                displayingName = "돌봄종류",
                itemsType = FilterItemsType.Caring(
                    isAllChecked = false, list = listOf(
                        CaringType.NURSING,
                        CaringType.SCHOOL,
                        CaringType.HOUSEKEEPING,
                        CaringType.PARENTING
                    )
                ),
                isSelected = false
            ),

            FilterType.NeighborhoodScope(
                displayingName = "동네범위",
                itemsType = FilterItemsType.NeighborhoodScope(list = listOf()),
                isSelected = false
            ),

            FilterType.Period(
                displayingName = "1회성/정기",
                itemsType = FilterItemsType.Period(list = listOf()),
                isSelected = false
            ),

            FilterType.Time(
                displayingName = "시간",
                itemsType = FilterItemsType.Time(
                    startTime = LocalTime.now(),
                    endTime = LocalTime.now()
                ),
                isSelected = false
            ),
        )
    )
    val filterItems: StateFlow<List<FilterType>> = _filterItems

//    val searchedJobOfferSummary: Flow<PagingData<JobOfferSummary>> =
//        caringRepository.fetchJobOfferSummary().cachedIn(viewModelScope)

}
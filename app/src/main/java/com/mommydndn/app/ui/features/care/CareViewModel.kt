package com.mommydndn.app.ui.features.care

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mommydndn.app.data.model.care.CaringType
import com.mommydndn.app.data.model.care.CaringTypeItem
import com.mommydndn.app.data.model.care.Filter.FilterItemsType
import com.mommydndn.app.data.model.care.Filter.FilterType
import com.mommydndn.app.data.model.care.SortingType
import com.mommydndn.app.data.model.care.SortingTypeItem
import com.mommydndn.app.data.model.care.WorkPeriodType
import com.mommydndn.app.data.model.common.DayOfWeekItem
import com.mommydndn.app.data.model.common.DayOfWeekType
import com.mommydndn.app.data.respository.CaringRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
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
                        SortingTypeItem(SortingType.LATEST, true),
                        SortingTypeItem(SortingType.MOST_VIEW),
                        SortingTypeItem(SortingType.HIGHEST_SALARY),
                        SortingTypeItem(SortingType.CLOSEST)
                    )
                ),
                isSelected = false
            ),

            FilterType.Caring(
                displayingName = "돌봄종류",
                itemsType = FilterItemsType.Caring(
                    isAllChecked = false, list = listOf()
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
                itemsType = FilterItemsType.Period(
                    list = listOf(
                        WorkPeriodType.REGULAR,
                        WorkPeriodType.ONETIME
                    )
                ),
                isSelected = false
            ),
            FilterType.Day(
                displayingName = "요일",
                itemsType = FilterItemsType.Day(
                    list = listOf(
                        DayOfWeekItem(DayOfWeekType.MON),
                        DayOfWeekItem(DayOfWeekType.TUE),
                        DayOfWeekItem(DayOfWeekType.WED),
                        DayOfWeekItem(DayOfWeekType.THU),
                        DayOfWeekItem(DayOfWeekType.FRI),
                        DayOfWeekItem(DayOfWeekType.SAT),
                        DayOfWeekItem(DayOfWeekType.SUN)
                    )
                ),
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

    init {
        fetchCaringTypeItems()
    }

    private fun fetchCaringTypeItems() {
        viewModelScope.launch {
            caringRepository.fetchCaringTypeItems().collect { types ->
                val currentFilterItems = _filterItems.value
                val caringFilter = currentFilterItems.find { it is FilterType.Caring } as? FilterType.Caring

                caringFilter?.itemsType?.list = types

                _filterItems.value = currentFilterItems
            }
        }
    }
    fun updateDayFilter(selectedFilters: FilterItemsType.Day) {
        val currentFilterItems = _filterItems.value
        val dayFilter = currentFilterItems.find { it is FilterType.Day } as? FilterType.Day
        dayFilter?.itemsType?.copy(selectedFilters.list)
    }

    fun updateCaringFilter(selectedFilters: FilterItemsType.Caring) {
        val currentFilterItems = _filterItems.value
        val dayFilter = currentFilterItems.find { it is FilterType.Caring } as? FilterType.Caring
        dayFilter?.itemsType?.copy(isAllChecked = selectedFilters.isAllChecked, list = selectedFilters.list)
    }
}
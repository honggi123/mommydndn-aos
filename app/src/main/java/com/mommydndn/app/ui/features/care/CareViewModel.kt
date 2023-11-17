package com.mommydndn.app.ui.features.care

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.mommydndn.app.data.api.model.request.JobOfferListRequest
import com.mommydndn.app.data.api.model.response.UserResponse
import com.mommydndn.app.data.model.care.CaringTypeItem
import com.mommydndn.app.data.model.care.Filter.FilterItemsType
import com.mommydndn.app.data.model.care.Filter.FilterType
import com.mommydndn.app.data.model.care.JobOfferSummaryListItem
import com.mommydndn.app.data.model.care.SortingType
import com.mommydndn.app.data.model.care.SortingTypeItem
import com.mommydndn.app.data.model.care.WorkPeriodType
import com.mommydndn.app.data.model.care.WorkPeriodTypeItem
import com.mommydndn.app.data.model.common.DayOfWeekItem
import com.mommydndn.app.data.model.common.DayOfWeekType
import com.mommydndn.app.data.respository.CaringRepository
import com.mommydndn.app.data.respository.UserRepository
import com.mommydndn.app.utils.DateTimeUtils
import com.mommydndn.app.utils.StringUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.time.LocalTime
import javax.inject.Inject


@HiltViewModel
class CareViewModel @Inject constructor(
    private val caringRepository: CaringRepository,
    private val userRepository: UserRepository
) : ViewModel() {

    val userInfo: StateFlow<UserResponse?> = userRepository.fetchUserInfo().stateIn(
        scope = viewModelScope,
        started = SharingStarted.Lazily,
        initialValue = null
    )

    private val _filterItems = MutableStateFlow(
        listOf(
            FilterType.Sorting(
                displayingName = "최신순",
                items = FilterItemsType.Sorting(),
                isSelected = true
            ),

            FilterType.NeighborhoodScope(
                displayingName = "${userInfo.value?.emd?.ctprvnName} 외 24",
                items = FilterItemsType.NeighborhoodScope(list = listOf(6, 9, 24)),
                isSelected = true
            ),

            FilterType.Caring(
                displayingName = "돌봄종류",
                items = FilterItemsType.Caring(isAllChecked = false),
                isSelected = false
            ),

            FilterType.Period(
                displayingName = "정기",
                items = FilterItemsType.Period(),
                isSelected = false
            ),
            FilterType.Day(
                displayingName = "요일",
                items = FilterItemsType.Day(),
                isSelected = false
            ),
            FilterType.Time(
                displayingName = "시간",
                items = FilterItemsType.Time(),
                isSelected = false
            ),
        )
    )
    val filterItems: StateFlow<List<FilterType>> = _filterItems

    val searchedJobOfferSummary: Flow<PagingData<JobOfferSummaryListItem>> =
        combine(userInfo, filterItems) { user, Items ->
            if (user != null) {
                caringRepository.fetchJobOfferSummary(
                    keyword = null,
                    caringTypeList = filterItems.value.filterIsInstance<FilterType.Caring>()
                        .first().items.list.filter { it.isSelected }.map { it.caringType },
                    days = filterItems.value.filterIsInstance<FilterType.Day>()
                        .first().items.list.filter { it.isSelected }.map { it.type },
                    emdId = userInfo.value?.emd?.id ?: 0,
                    sortingType = filterItems.value.filterIsInstance<FilterType.Sorting>()
                        .first().items.list.filter { it.isSelected }.first().sortingType,
                    workPeriodTypeList = filterItems.value.filterIsInstance<FilterType.Period>()
                        .first().items.list.filter { it.isSelected }.map { it.workPeriodType },
                    neighborhoodScope = 24,
                    startTime = filterItems.value.filterIsInstance<FilterType.Time>()
                        .first().items.startTime ?: null,
                    endTime = filterItems.value.filterIsInstance<FilterType.Time>()
                        .first().items.endTime ?: null,
                ).cachedIn(viewModelScope)
            } else emptyFlow()
        }.flatMapLatest { it }

    init {
        viewModelScope.launch {
            fetchCaringTypeItems()

            userInfo.collect { userResponse ->
                updateFilterItems(userResponse)
            }
        }
    }

    private fun updateFilterItems(userInfo: UserResponse?) {
        _filterItems.value = _filterItems.value.map { filterType ->
            when (filterType) {
                is FilterType.NeighborhoodScope -> {
                    filterType.copy(displayingName = "${userInfo?.emd?.name} 외 24")
                }

                else -> filterType
            }
        }
    }

    private suspend fun fetchCaringTypeItems() {
        caringRepository.fetchCaringTypeItems().collect { items ->
            val currentFilterItems = _filterItems.value
            val caringFilter =
                currentFilterItems.find { it is FilterType.Caring } as? FilterType.Caring
            val seletedItems = items.map { it.copy(isSelected = true) }
            caringFilter?.items?.list = seletedItems

            _filterItems.value = currentFilterItems
        }
    }

    fun updateSortingFilter(selectedFilters: FilterItemsType.Sorting) {
        val currentFilterItems = _filterItems.value
        val sortingFilter = currentFilterItems.filterIsInstance<FilterType.Sorting>().first()

        val updatedFilter = sortingFilter.copy(
            isSelected = true,
            displayingName = selectedFilters.list.filter { it.isSelected }
                .first().sortingType.diaplayingName,
            items = FilterItemsType.Sorting(selectedFilters.list)
        )

        _filterItems.value = currentFilterItems.toMutableList().apply {
            set(indexOf(sortingFilter), updatedFilter)
        }
    }

    fun updateDayFilter(selectedFilters: FilterItemsType.Day) {
        val currentFilterItems = _filterItems.value
        val dayFilter = currentFilterItems.filterIsInstance<FilterType.Day>().first()

        val updatedFilter = dayFilter.copy(
            isSelected = true,
            displayingName = StringUtils.getConcatenatedString(selectedFilters.list.filter { it.isSelected }
                .map { it.type.displayingName }),
            items = FilterItemsType.Day(selectedFilters.list)
        )

        _filterItems.value = currentFilterItems.toMutableList().apply {
            set(indexOf(dayFilter), updatedFilter)
        }
    }

    fun updateCaringFilter(selectedFilters: FilterItemsType.Caring) {
        val currentFilterItems = _filterItems.value
        val caringFilter = currentFilterItems.filterIsInstance<FilterType.Caring>().first()

        val updatedFilter = caringFilter.copy(
            isSelected = true,
            displayingName = StringUtils.getConcatenatedCommasString(selectedFilters.list.filter { it.isSelected }
                .map { it.caringType.value }),
            items = caringFilter.items.copy(
                isAllChecked = selectedFilters.isAllChecked,
                list = selectedFilters.list
            )
        )

        _filterItems.value = currentFilterItems.toMutableList().apply {
            set(indexOf(caringFilter), updatedFilter)
        }
    }

    fun updateTimeFilter(selectedFilters: FilterItemsType.Time) {
        val currentFilterItems = _filterItems.value
        val timeFilter = currentFilterItems.filterIsInstance<FilterType.Time>().first()

        val updatedFilter = timeFilter.copy(
            isSelected = true,
            displayingName = getDutarionHourString(
                selectedFilters.startTime,
                selectedFilters.endTime
            ) ?: "시간",
            items = timeFilter.items.copy(
                startTime = selectedFilters.startTime,
                endTime = selectedFilters.endTime
            )
        )

        _filterItems.value = currentFilterItems.toMutableList().apply {
            set(indexOf(timeFilter), updatedFilter)
        }
    }

    fun updatePeriodFilter(selectedFilters: FilterItemsType.Period) {
        val currentFilterItems = _filterItems.value
        val periodFilter = currentFilterItems.filterIsInstance<FilterType.Period>().first()

        val updatedFilter = periodFilter.copy(
            isSelected = true,
            displayingName = selectedFilters.list.filter { it.isSelected }
                .first().workPeriodType.value,
            items = periodFilter.items.copy(
                list = selectedFilters.list
            )
        )

        _filterItems.value = currentFilterItems.toMutableList().apply {
            set(indexOf(periodFilter), updatedFilter)
        }
    }

    private fun getDutarionHourString(startTime: LocalTime?, endTime: LocalTime?): String? {
        if (startTime == null || endTime == null) {
            return null
        }
        return "${DateTimeUtils.getLocalTimeText(startTime!!)} ~ ${
            DateTimeUtils.getLocalTimeText(
                endTime!!
            )
        }"
    }
}


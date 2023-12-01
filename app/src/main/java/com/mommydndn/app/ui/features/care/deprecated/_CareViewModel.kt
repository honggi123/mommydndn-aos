package com.mommydndn.app.ui.features.care.deprecated

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.mommydndn.app.data.api.model.response.GetUserResponse
import com.mommydndn.app.data.model.care.filter.FilterItemsType
import com.mommydndn.app.data.model.care.filter.FilterType
import com.mommydndn.app.data.model.care.summary.CompanySummaryListItem
import com.mommydndn.app.data.model.care.summary.JobOfferSummaryListItem
import com.mommydndn.app.data.model.care.summary.JobSeekerSummaryItem
import com.mommydndn.app.domain.repository.CaringRepository
import com.mommydndn.app.domain.repository.UserRepository
import com.mommydndn.app.ui.models.care.SummaryTabType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class _CareViewModel @Inject constructor(
    private val caringRepository: CaringRepository,
    private val userRepository: UserRepository
) : ViewModel() {

    /*
    val userInfo: StateFlow<GetUserResponse?> = userRepository.getUser().stateIn(
        scope = viewModelScope,
        started = SharingStarted.Lazily,
        initialValue = null
    )

    private val _filterItems = MutableStateFlow(listOf<FilterType>())
    val filterItems: StateFlow<List<FilterType>> = _filterItems

    private val _selectedTab = MutableStateFlow<SummaryTabType?>(null)
    val selectedTab: StateFlow<SummaryTabType?> = _selectedTab

    val searchedJobOfferSummary: Flow<PagingData<JobOfferSummaryListItem>> =
        combine(userInfo, filterItems, selectedTab) { user, Items, tab ->
            if (user != null && tab == SummaryTabType.JOB_OFFER) {
                caringRepository.fetchJobOfferSummary(
                    keyword = null,
                    caringTypeList = filterItems.value.filterIsInstance<FilterType.Caring>()
                        .first().items.list.filter { it.isSelected }.map { it.caringType },
                    days = filterItems.value.filterIsInstance<FilterType.Day>()
                        .first().items.list.filter { it.isSelected }.map { it.type },
                    emdId = userInfo.value?.emd?.id ?: 0,
                    sortingType = filterItems.value.filterIsInstance<FilterType.Sorting>()
                        .first().items.list.filter { it.isSelected }.first().sortingType,
                    workPeriodType = filterItems.value.filterIsInstance<FilterType.Period>()
                        .first().items.list.filter { it.isSelected }.map { it.workPeriodType }.first(),
                    neighborhoodScope = filterItems.value.filterIsInstance<FilterType.NeighborhoodScope>()
                        .first().items.list.filter { it.isSelected }
                        .first().distantceType.distantce,
                    startTime = filterItems.value.filterIsInstance<FilterType.Time>()
                        .first().items.startTime ?: null,
                    endTime = filterItems.value.filterIsInstance<FilterType.Time>()
                        .first().items.endTime ?: null,
                ).cachedIn(viewModelScope)
            } else emptyFlow()
        }.flatMapLatest { it }

    val searchedJobSeekerSummary: Flow<PagingData<JobSeekerSummaryItem>> =
        combine(userInfo, filterItems, selectedTab) { user, Items, tab ->
            if (user != null && tab == SummaryTabType.JOB_SEEKER) {
                caringRepository.fetchJobSeekerSummary(
                    keyword = null,
                    caringTypeList = filterItems.value.filterIsInstance<FilterType.Caring>()
                        .first().items.list.filter { it.isSelected }.map { it.caringType },
                    emdId = userInfo.value?.emd?.id ?: 0,
                    sortingType = filterItems.value.filterIsInstance<FilterType.Sorting>()
                        .first().items.list.filter { it.isSelected }.first().sortingType,
                    neighborhoodScope = filterItems.value.filterIsInstance<FilterType.NeighborhoodScope>()
                        .first().items.list.filter { it.isSelected }
                        .first().distantceType.distantce
                ).cachedIn(viewModelScope)
            } else emptyFlow()
        }.flatMapLatest { it }

    val searchedCompanySummary: Flow<PagingData<CompanySummaryListItem>> =
        combine(userInfo, filterItems, selectedTab) { user, Items, tab ->
            if (user != null && tab == SummaryTabType.COMPANY) {
                caringRepository.fetchCompanySummary(
                    keyword = null,
                    caringTypeList = filterItems.value.filterIsInstance<FilterType.Caring>()
                        .first().items.list.filter { it.isSelected }.map { it.caringType },
                    emdId = userInfo.value?.emd?.id ?: 0,
                    sortingType = filterItems.value.filterIsInstance<FilterType.Sorting>()
                        .first().items.list.filter { it.isSelected }.first().sortingType,
                    neighborhoodScope = filterItems.value.filterIsInstance<FilterType.NeighborhoodScope>()
                        .first().items.list.filter { it.isSelected }
                        .first().distantceType.distantce
                ).cachedIn(viewModelScope)
            } else emptyFlow()
        }.flatMapLatest { it }


    init {
        updateTabPosition(0)

        viewModelScope.launch {
            userInfo.collect { userResponse ->
                updateNeighborhoodFilterItems(userResponse)
            }
        }
    }

    private fun updateNeighborhoodFilterItems(userInfo: GetUserResponse?) {
        _filterItems.value = _filterItems.value.map { filterType ->
            when (filterType) {
                is FilterType.NeighborhoodScope -> {
                    filterType.copy(
                        displayingName = "${userInfo?.emd?.name} 외 24",
                        items = filterType.items.copy(myLocationName = userInfo?.emd?.name ?: "")
                    )
                }

                else -> filterType
            }
        }
    }

    fun updateTabPosition(status: Int) {
        _selectedTab.value = SummaryTabType.find(status)
        updateFilterItems(_selectedTab.value)
    }

    private fun updateFilterItems(selectedSummaryTab: SummaryTabType?) {
        val list = when (selectedSummaryTab) {
            SummaryTabType.JOB_OFFER -> listOf(
                FilterType.Sorting(
                    items = FilterItemsType.Sorting(),
                ),
                FilterType.NeighborhoodScope(
                    displayingName = "${userInfo.value?.emd?.name} 외 24",
                    items = FilterItemsType.NeighborhoodScope(
                        myLocationName = userInfo.value?.emd?.name ?: ""
                    ),
                ),
                FilterType.Caring(
                    items = FilterItemsType.Caring(),
                ),
                FilterType.Day(
                    items = FilterItemsType.Day(),
                ),
                FilterType.Time(
                    items = FilterItemsType.Time(),
                ),
                FilterType.Period(
                    items = FilterItemsType.Period(),
                ),
            )

            SummaryTabType.JOB_SEEKER, SummaryTabType.COMPANY -> listOf(
                FilterType.Sorting(
                    items = FilterItemsType.Sorting(),
                ),

                FilterType.NeighborhoodScope(
                    displayingName = "${userInfo.value?.emd?.name} 외 24",
                    items = FilterItemsType.NeighborhoodScope(
                        myLocationName = userInfo.value?.emd?.name ?: ""
                    )
                ),

                FilterType.Caring(
                    items = FilterItemsType.Caring(),
                )
            )

            else -> listOf()
        }

        _filterItems.value = list
    }

    fun updateSortingFilter(selectedFilters: FilterItemsType.Sorting) {
        val currentFilterItems = _filterItems.value
        val sortingFilter = currentFilterItems.filterIsInstance<FilterType.Sorting>().first()

        val updatedFilter = sortingFilter.copy(
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
            items = caringFilter.items.copy(
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
            items = timeFilter.items.copy(
                startTime = selectedFilters.startTime,
                endTime = selectedFilters.endTime
            )
        )

        _filterItems.value = currentFilterItems.toMutableList().apply {
            set(indexOf(timeFilter), updatedFilter)
        }
    }

    fun updateNeighborhoodScopeFilter(selectedFilters: FilterItemsType.NeighborhoodScope) {
        val currentFilterItems = _filterItems.value
        val scopeFilter =
            currentFilterItems.filterIsInstance<FilterType.NeighborhoodScope>().first()

        val updatedFilter = scopeFilter.copy(
            displayingName = "${userInfo.value?.emd?.name} 외 ${
                selectedFilters.list.filter { it.isSelected }
                    .first().distantceType.distantce
            }",
            items = scopeFilter.items.copy(
                list = selectedFilters.list
            )
        )

        _filterItems.value = currentFilterItems.toMutableList().apply {
            set(indexOf(scopeFilter), updatedFilter)
        }
    }


    fun updatePeriodFilter(selectedFilters: FilterItemsType.Period) {
        val currentFilterItems = _filterItems.value
        val periodFilter = currentFilterItems.filterIsInstance<FilterType.Period>().first()

        val updatedFilter = periodFilter.copy(
            items = periodFilter.items.copy(
                list = selectedFilters.list
            )
        )

        _filterItems.value = currentFilterItems.toMutableList().apply {
            set(indexOf(periodFilter), updatedFilter)
        }
    }
     */
}


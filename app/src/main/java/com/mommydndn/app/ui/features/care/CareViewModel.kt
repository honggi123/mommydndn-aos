package com.mommydndn.app.ui.features.care

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mommydndn.app.domain.model.care.CareJobOpening
import com.mommydndn.app.domain.model.care.NearbyNeighborhoodDistance
import com.mommydndn.app.domain.model.user.NearbyNeighborhoods
import com.mommydndn.app.domain.model.user.Neighborhood
import com.mommydndn.app.domain.usecase.care.GetNearbyCareJobOpeningsParams
import com.mommydndn.app.domain.usecase.care.GetNearbyCareJobOpeningsUseCase
import com.mommydndn.app.domain.usecase.invoke
import com.mommydndn.app.domain.usecase.user.GetNearbyNeighborhoodDistanceUseCase
import com.mommydndn.app.domain.usecase.user.GetNearbyNeighborhoodsUseCase
import com.mommydndn.app.domain.usecase.user.GetNeighborhoodUseCase
import com.mommydndn.app.ui.features.care.filters.CareFilter
import com.mommydndn.app.ui.features.care.filters.CareFilters
import com.mommydndn.app.ui.features.care.filters.CareOrderBy
import com.mommydndn.app.ui.features.care.filters.CareTypesFilter
import com.mommydndn.app.ui.features.care.filters.DaysOfWeekFilter
import com.mommydndn.app.ui.features.care.filters.NeighborhoodsFilter
import com.mommydndn.app.ui.features.care.filters.PayFilter
import com.mommydndn.app.ui.features.care.filters.WorkHoursFilter
import com.mommydndn.app.ui.features.care.filters.WorkPeriodFilter
import com.mommydndn.app.util.result.data
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.filterNot
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class CareViewModel @Inject constructor(
    getNeighborhoodUseCase: GetNeighborhoodUseCase,
    getNearbyNeighborhoodDistanceUseCase: GetNearbyNeighborhoodDistanceUseCase,
    getNearbyNeighborhoodsUseCase: GetNearbyNeighborhoodsUseCase,
    private val getNearbyCareJobOpeningsUseCase: GetNearbyCareJobOpeningsUseCase,
) : ViewModel() {

    /**
     * order,
     * neighborhoods,
     * care_type,
     * pay,
     * days_of_week,
     * working_hours,
     * care_period
     */
    private val order = MutableStateFlow(CareOrderBy.LATEST)

    fun setOrder(order: CareOrderBy) {
        this.order.value = order
    }

    private val neighborhood: StateFlow<Neighborhood?> = getNeighborhoodUseCase()
        .map { result -> result.data }
        .filterNotNull()
        .stateIn(viewModelScope, SharingStarted.Eagerly, null)

    private val nearbyNeighborhoodDistance: StateFlow<NearbyNeighborhoodDistance> =
        getNearbyNeighborhoodDistanceUseCase()
            .map { result -> result.data }
            .filterNotNull()
            .stateIn(
                viewModelScope,
                SharingStarted.Eagerly,
                NearbyNeighborhoodDistance.VERY_DISTANT
            )

    private val nearbyNeighborhoods: StateFlow<NearbyNeighborhoods> = get

    private val _filters = MutableStateFlow(CareFilters())

    private val filters: StateFlow<List<CareFilter>> = neighborhood.filterNotNull()
        .combine(nearbyNeighborhoodDistance) { neighborhood, nearbyNeighborhoodDistance ->
            NeighborhoodsFilter(neighborhood, nearbyNeighborhoodDistance)
        }.combine(_filters) { neighborhoodsFilter, filters ->
            filters.toMutableMap()
                .apply { put(neighborhoodsFilter.javaClass, neighborhoodsFilter) }
                .values
                .toList()
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    private val jobOpenings: StateFlow<List<CareJobOpening>> = neighborhood.filterNotNull()
        .flatMapLatest { neighborhood ->
            with(neighborhood) {
                getNearbyCareJobOpeningsUseCase(
                    GetNearbyCareJobOpeningsParams(latitude, longitude)
                )
            }
        }
        .conflate() // flatMapLatest buffered
        .map { it.data ?: emptyList() }
        .filterNot { it.isEmpty() }
        .stateIn(viewModelScope, SharingStarted.Eagerly, emptyList())

    private val filteredJobOpenings = jobOpenings.combine(filters) { jobOpenings, filters ->
        jobOpenings.filter {

        }
    }

    val uiState: StateFlow<CareUiState> =
        combine(
            neighborhood.filterNotNull(),
            order,
            filters,
            jobOpenings,
        ) { neighborhood, order, filters, jobOpenings ->
            CareUiState.Success(
                neighborhood = neighborhood,
                order = order,
                filters = filters,
                jobOpeningListItems = jobOpenings
            )
        }.stateIn(viewModelScope, SharingStarted.Eagerly, CareUiState.Loading)


    fun setFilter(filter: CareFilter) {
        _filters.value = filters.value
            .associateBy { it::class.java }
            .toMutableMap()
            .apply { put(filter.javaClass, filter) }
    }
}
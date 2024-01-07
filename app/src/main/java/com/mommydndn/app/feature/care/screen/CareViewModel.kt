package com.mommydndn.app.feature.care.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mommydndn.app.domain.model.care.CareJobOpening
import com.mommydndn.app.domain.model.user.NearbyNeighborhoods
import com.mommydndn.app.domain.model.user.Neighborhood
import com.mommydndn.app.domain.model.user.NeighborhoodDistance
import com.mommydndn.app.domain.usecase.care.GetNearbyCareJobOpeningsParams
import com.mommydndn.app.domain.usecase.care.GetNearbyCareJobOpeningsUseCase
import com.mommydndn.app.domain.usecase.invoke
import com.mommydndn.app.domain.usecase.user.GetNearbyNeighborhoodsParams
import com.mommydndn.app.domain.usecase.user.GetNearbyNeighborhoodsUseCase
import com.mommydndn.app.domain.usecase.user.GetNeighborhoodDistanceUseCase
import com.mommydndn.app.domain.usecase.user.GetNeighborhoodUseCase
import com.mommydndn.app.feature.care.filters.CareFilter
import com.mommydndn.app.feature.care.filters.CareOrderBy
import com.mommydndn.app.feature.care.filters.NeighborhoodFilter
import com.mommydndn.app.utils.result.data
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
    getNeighborhoodDistanceUseCase: GetNeighborhoodDistanceUseCase,
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

    private val neighborhoodDistance: StateFlow<NeighborhoodDistance> =
        getNeighborhoodDistanceUseCase()
            .map { result -> result.data }
            .filterNotNull()
            .stateIn(viewModelScope, SharingStarted.Eagerly, NeighborhoodDistance.VERY_DISTANT)

    private val nearbyNeighborhoods = neighborhood.filterNotNull()
        .map { neighborhood ->
            with(neighborhood) {
                getNearbyNeighborhoodsUseCase(
                    GetNearbyNeighborhoodsParams(latitude, longitude)
                )
            }
        }
        .stateIn(viewModelScope, SharingStarted.Eagerly, NearbyNeighborhoods())

    private val _filters =
        MutableStateFlow<Map<Class<out CareFilter<*>>, CareFilter<*>>>(emptyMap())

    private val filters = neighborhood.filterNotNull()
        .map { neighborhood -> NeighborhoodFilter(neighborhood) }
        .combine(_filters) { neighborhoodsFilter, filters ->
            filters
                .toMutableMap()
                .apply { put(neighborhoodsFilter.javaClass, neighborhoodsFilter) }
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyMap())

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

    /*
    private val filteredJobOpenings = jobOpenings.combine(filters) { jobOpenings, filters ->
        jobOpenings.filter {

        }
    }
     */

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
                filters = filters.values.toList(),
                jobOpeningListItems = emptyList() // todo
            )
        }.stateIn(viewModelScope, SharingStarted.Eagerly, CareUiState.Loading)


    fun setFilter(filter: CareFilter<*>) {
        _filters.value = filters.value
            .toMutableMap()
            .apply { put(filter.javaClass, filter) }
    }
}
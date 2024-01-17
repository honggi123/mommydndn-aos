package com.mommydndn.app.ui.care

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mommydndn.app.domain.model.NearbyNeighborhoodDistance
import com.mommydndn.app.domain.model.Neighborhood
import com.mommydndn.app.domain.model.care.CareJobOpening
import com.mommydndn.app.domain.usecase.care.GetNearbyCareJobOpeningsParams
import com.mommydndn.app.domain.usecase.care.GetNearbyCareJobOpeningsUseCase
import com.mommydndn.app.domain.usecase.invoke
import com.mommydndn.app.domain.usecase.user.GetNearbyNeighborhoodDistanceUseCase
import com.mommydndn.app.domain.usecase.user.GetNearbyNeighborhoodsUseCase
import com.mommydndn.app.domain.usecase.user.GetNeighborhoodUseCase
import com.mommydndn.app.ui.care.filter.CareFilters
import com.mommydndn.app.ui.care.filter.CareOrderBy
import com.mommydndn.app.ui.care.filter.NeighborhoodFilter
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

// TODO
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

    private val neighborhoodDistance: StateFlow<NearbyNeighborhoodDistance> =
        getNearbyNeighborhoodDistanceUseCase()
            .map { result -> result.data }
            .filterNotNull()
            .stateIn(viewModelScope, SharingStarted.Eagerly, NearbyNeighborhoodDistance.VERY_DISTANT)

    private val nearbyNeighborhoods: StateFlow<Map<NearbyNeighborhoodDistance, List<Neighborhood>>> = TODO()
    /*
    neighborhood.filterNotNull()
        .map { neighborhood ->
            with(neighborhood.coordinates) {
                getNearbyNeighborhoodsUseCase(
                    GetNearbyNeighborhoodsParams(latitude, longitude)
                )
            }
        }
        .stateIn(viewModelScope, SharingStarted.Eagerly, NearbyNeighborhoods())
     */

    private val _filters =
        MutableStateFlow<Map<Class<out CareFilters<*>>, CareFilters<*>>>(emptyMap())

    private val filters = neighborhood.filterNotNull()
        .map { neighborhood -> NeighborhoodFilter(neighborhood) }
        .combine(_filters) { neighborhoodsFilter, filters ->
            filters
                .toMutableMap()
                .apply { put(neighborhoodsFilter.javaClass, neighborhoodsFilter) }
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyMap())

    private val jobOpenings: StateFlow<List<CareJobOpening>> = neighborhood.filterNotNull()
        .flatMapLatest { neighborhood ->
            with(neighborhood.coordinates) {
                getNearbyCareJobOpeningsUseCase(
                    GetNearbyCareJobOpeningsParams(latitude, longitude)
                )
            }
        }
        .conflate() // flatMapLatest buffered
        .map { it.data ?: emptyList() }
        .filterNot { it.isEmpty() }
        .stateIn(viewModelScope, SharingStarted.Eagerly, emptyList())

    val uiState: StateFlow<CareUiState> =
        combine(
            neighborhood.filterNotNull(),
            order,
            filters,
            jobOpenings,
        ) { neighborhood, order, filters, jobOpenings ->
            CareUiState.Success(
                neighborhood = with(neighborhood) {
                    TODO()
                },
                order = order,
                filters = filters.values.toList(),
                jobs = emptyList() // todo
            )
        }.stateIn(viewModelScope, SharingStarted.Eagerly, CareUiState.Loading)


    fun setFilter(filter: CareFilters<*>) {
        _filters.value = filters.value
            .toMutableMap()
            .apply { put(filter.javaClass, filter) }
    }
}
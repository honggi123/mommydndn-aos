package com.mommydndn.app.ui.features.care

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mommydndn.app.domain.model.care.NearbyNeighborhoodDistance
import com.mommydndn.app.domain.model.user.Neighborhood
import com.mommydndn.app.domain.usecase.invoke
import com.mommydndn.app.domain.usecase.user.GetNearbyNeighborhoodDistanceUseCase
import com.mommydndn.app.domain.usecase.user.GetNeighborhoodUseCase
import com.mommydndn.app.util.result.data
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.filterNot
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class CareViewModel @Inject constructor(
    getNeighborhoodUseCase: GetNeighborhoodUseCase,
    getNearbyNeighborhoodDistanceUseCase: GetNearbyNeighborhoodDistanceUseCase,
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
    private val order = MutableStateFlow(CareOrder.LATEST)

    fun setOrder(order: CareOrder) {
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
            .stateIn(viewModelScope, SharingStarted.Eagerly, NearbyNeighborhoodDistance.VERY_DISTANT)

    private val _filters = MutableStateFlow<Map<Class<out CareFilter>, CareFilter>>(emptyMap())
    private val filters: StateFlow<List<CareFilter>> = neighborhood.filterNotNull()
        .combine(nearbyNeighborhoodDistance) { neighborhood, nearbyNeighborhoodDistance ->
            NeighborhoodsFilter(neighborhood, nearbyNeighborhoodDistance)
        }.combine(_filters) { neighborhoodsFilter, filters ->
            filters.toMutableMap()
                .apply { put(NeighborhoodsFilter::class.java, neighborhoodsFilter) }
                .values
                .toList()
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    val uiState: StateFlow<CareUiState> =
        combine(
            neighborhood.filterNotNull(),
            order,
            filters.filterNot { it.isEmpty() },
        ) { neighborhood, order, filters ->
            CareUiState.Success(
                neighborhood = neighborhood,
                order = order,
                filters = filters
            )
        }.stateIn(viewModelScope, SharingStarted.Eagerly, CareUiState.Loading)


    fun setFilter(filter: CareFilter) {
        _filters.value = filters.value
            .associateBy { it::class.java }
            .toMutableMap()
            .apply { put(filter.javaClass, filter) }
    }
}
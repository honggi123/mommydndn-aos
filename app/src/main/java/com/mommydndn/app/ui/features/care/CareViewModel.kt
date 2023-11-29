package com.mommydndn.app.ui.features.care

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mommydndn.app.domain.model.care.NearbyNeighborhoodDistance
import com.mommydndn.app.domain.model.user.NEIGHBORHOOD_DUMMY
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

/**
 * order,
 * neighborhoods,
 * care_type,
 * pay,
 * days_of_week,
 * working_hours,
 * care_period
 */
@HiltViewModel
class CareViewModel @Inject constructor() : ViewModel() {

    // todo: get user ..
    private val _neighborhood = MutableStateFlow(NEIGHBORHOOD_DUMMY)

    private val _nearbyNeighborhoodDistance = MutableStateFlow(NearbyNeighborhoodDistance.NEARBY)

    private val _order = MutableStateFlow(CareOrder.LATEST)
    val order = _order.asStateFlow()

    private val _filters = MutableStateFlow<CareFilters?>(null)

    val filters = combine(
        _neighborhood,
        _nearbyNeighborhoodDistance,
        _filters
    ) { neighborhood, nearbyNeighborhoodDistance, filters ->
        val nearbyNeighborhoodsCount = when (nearbyNeighborhoodDistance) {
            NearbyNeighborhoodDistance.IMMEDIATE -> 0
            NearbyNeighborhoodDistance.NEARBY -> neighborhood.nearbyNeighborhoods.size
            NearbyNeighborhoodDistance.DISTANT -> neighborhood.distantNeighborhoods.size
            NearbyNeighborhoodDistance.VERY_DISTANT -> neighborhood.veryDistantNeighborhoods.size
        }

        val neighborhoodsFilter = NeighborhoodsFilter(
            neighborhood.name,
            nearbyNeighborhoodDistance,
            nearbyNeighborhoodsCount
        )

        filters?.copy(neighborhoods = neighborhoodsFilter) ?: CareFilters(neighborhoodsFilter)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), null)

    fun updateFilters(filters: CareFilters) {
        _filters.value = filters
    }
}
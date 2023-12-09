package com.mommydndn.app.ui.features.care.filters

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.mommydndn.app.R
import com.mommydndn.app.domain.model.care.NearbyNeighborhoodDistance
import com.mommydndn.app.domain.model.user.NearbyNeighborhoods
import com.mommydndn.app.domain.model.user.Neighborhood

data class NeighborhoodsFilter(
    val neighborhood: Neighborhood = Neighborhood(
        id = 0,
        name = "",
        latitude = 0.0,
        longitude = 0.0,
    ),
    val nearbyNeighborhoodDistance: NearbyNeighborhoodDistance = NearbyNeighborhoodDistance.VERY_DISTANT,
    val nearbyNeighborhoods: NearbyNeighborhoods = NearbyNeighborhoods(
        nearbyNeighborhoods = emptyList(),
        distantNeighborhoods = emptyList(),
        veryDistantNeighborhoods = emptyList(),
    )
) : CareFilter<Nothing> {

    override val hasValue: Boolean = true

    @Composable
    override fun displayName(): String {
        val nearbyNeighborhoodsCount = nearbyNeighborhoodsCount(
            nearbyNeighborhoodDistance,
            nearbyNeighborhoods
        )

        val postfix = if (nearbyNeighborhoodsCount == 0) {
            ""
        } else {
            stringResource(R.string.other_size, nearbyNeighborhoodsCount)
        }

        return neighborhood.name + postfix
    }

    private fun nearbyNeighborhoodsCount(
        distance: NearbyNeighborhoodDistance,
        neighborhoods: NearbyNeighborhoods
    ): Int = when (distance) {
        NearbyNeighborhoodDistance.IMMEDIATE -> 0
        NearbyNeighborhoodDistance.NEARBY -> neighborhoods.nearbyNeighborhoods.size
        NearbyNeighborhoodDistance.DISTANT -> neighborhoods.distantNeighborhoods.size
        NearbyNeighborhoodDistance.VERY_DISTANT -> neighborhoods.veryDistantNeighborhoods.size
    }

    override fun predicate(value: Nothing): Boolean = true
}
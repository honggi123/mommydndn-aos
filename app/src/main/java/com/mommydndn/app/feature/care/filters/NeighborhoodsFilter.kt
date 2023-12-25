package com.mommydndn.app.feature.care.filters

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.mommydndn.app.R
import com.mommydndn.app.domain.model.user.NearbyNeighborhoods
import com.mommydndn.app.domain.model.user.Neighborhood
import com.mommydndn.app.domain.model.user.NeighborhoodDistance

data class NeighborhoodsFilter(
    val neighborhood: Neighborhood = Neighborhood(
        id = 0,
        name = "",
        address = "",
        latitude = 0.0,
        longitude = 0.0,
        distance = NeighborhoodDistance.VERY_DISTANT,
        nearbyNeighborhoods = NearbyNeighborhoods(),
    ),
) : CareFilter<Nothing> {

    override val selected: Boolean = true

    @Composable
    override fun displayName(): String {
        val nearbyNeighborhoodsSize = with(neighborhood.nearbyNeighborhoods) {
            when (neighborhood.distance) {
                NeighborhoodDistance.IMMEDIATE -> immediateNeighborhoods.size
                NeighborhoodDistance.NEARBY -> nearbyNeighborhoods.size
                NeighborhoodDistance.DISTANT -> distantNeighborhoods.size
                NeighborhoodDistance.VERY_DISTANT -> veryDistantNeighborhoods.size
            }
        }

        return neighborhood.name + nearbyNeighborhoodsSize.takeIf { it > 0 }
            ?.let { stringResource(R.string.other_size, it) }
            .orEmpty()
    }

    override fun predicate(value: Nothing): Boolean = true
}

@Composable
fun Neighborhood.displayName(): String {
    val nearbyNeighborhoodsSize = with(nearbyNeighborhoods) {
        when (distance) {
            NeighborhoodDistance.IMMEDIATE -> immediateNeighborhoods.size
            NeighborhoodDistance.NEARBY -> nearbyNeighborhoods.size
            NeighborhoodDistance.DISTANT -> distantNeighborhoods.size
            NeighborhoodDistance.VERY_DISTANT -> veryDistantNeighborhoods.size
        }
    }

    return name + nearbyNeighborhoodsSize.takeIf { it > 0 }
        ?.let { stringResource(R.string.nearby_neighborhoods_count, it) }
        .orEmpty()
}
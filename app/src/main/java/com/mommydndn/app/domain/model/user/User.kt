package com.mommydndn.app.domain.model.user

data class Neighborhood(
    val id: Int = 0,
    val name: String = "",
    val address: String = "",
    val latitude: Double = 0.0,
    val longitude: Double = 0.0,
    val distance: NeighborhoodDistance = NeighborhoodDistance.VERY_DISTANT,
    val nearbyNeighborhoods: NearbyNeighborhoods = NearbyNeighborhoods(),
)

enum class NeighborhoodDistance {
    IMMEDIATE,
    NEARBY,
    DISTANT,
    VERY_DISTANT
}

data class NearbyNeighborhoods(
    val immediateNeighborhoods: List<Neighborhood> = emptyList(),
    val nearbyNeighborhoods: List<Neighborhood> = emptyList(),
    val distantNeighborhoods: List<Neighborhood> = emptyList(),
    val veryDistantNeighborhoods: List<Neighborhood> = emptyList(),
)
package com.mommydndn.app.domain.model

data class Neighborhood(
    val id: Long,
    val name: String,
    val address: String,
    val coordinates: Coordinates,
)

data class Coordinates(
    val latitude: Double,
    val longitude: Double
)

enum class NearbyNeighborhoodDistance {
    IMMEDIATE,
    NEARBY,
    DISTANT,
    VERY_DISTANT
}
package com.mommydndn.app.domain.model

data class Neighborhood(
    val id: Long,
    val name: String,
    val address: String,
)

enum class NearbyNeighborhoodDistance {
    Immediate,
    Nearby,
    Distant,
    VeryDistant
}

data class Coordinates(
    val latitude: Latitude,
    val longitude: Longitude
)

@JvmInline
value class Latitude(val value: Double) {
    init {
        require(value in -90.0..90.0)
    }
}

@JvmInline
value class Longitude(val value: Double) {
    init {
        require(value in -180.0..180.0)
    }
}
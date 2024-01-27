package com.mommydndn.app.domain.model

data class Neighborhood(
    val id: String,
    val address: String,
    val province: String, // 도
    val city: String, // 시, 군, 구
    val name: String, // 동 이름
)

enum class NearbyNeighborhoodDistance {
    Immediate,
    Close,
    Distant,
    Far
}

data class AvailableNeighborhoods(
    val neighborhood: Neighborhood,
    val nearbyNeighborhoods: List<Neighborhood>,
)

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
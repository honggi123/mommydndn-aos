package com.mommydndn.app.domain.model.user

data class User(
    val id: Int,
    val name: String,
    val profileUrl: String?,
)

data class Neighborhood(
    val id: Int,
    val name: String,
    // todo
    val latitude: Double = 0.0,
    val longitude: Double = 0.0,
)

data class NearbyNeighborhoods(
    val nearbyNeighborhoods: List<Neighborhood>,
    val distantNeighborhoods: List<Neighborhood>,
    val veryDistantNeighborhoods: List<Neighborhood>,
)
package com.mommydndn.app.domain.model.user

data class User(
    val id: Int,
    val name: String,
    val avatarUrl: String?
)

enum class UserType {
    INDIVIDUAL,
    COMPANY
}

data class Neighborhood(
    val name: String,
    val latitude: Double,
    val longitude: Double,
    val nearbyNeighborhoods: List<Neighborhood>,
    val distantNeighborhoods: List<Neighborhood>,
    val veryDistantNeighborhoods: List<Neighborhood>,
)

val NEIGHBORHOOD_DUMMY = Neighborhood(
    name = "서초동",
    latitude = 37.488514,
    longitude = 127.016222,
    nearbyNeighborhoods = emptyList(),
    distantNeighborhoods = emptyList(),
    veryDistantNeighborhoods = emptyList(),
)
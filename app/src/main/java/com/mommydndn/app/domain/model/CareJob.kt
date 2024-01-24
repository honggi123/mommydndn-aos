package com.mommydndn.app.domain.model

data class CareJob(
    val id: Long,
    val employer: User,
    val title: String,
    val content: String,
    val careTypes: List<CareType>,
    val workAvailability: WorkAvailability,
    val location: Location,
    val pay: Pay,
    val images: List<Image>,
    val tags: List<String>,
    val applicantsCount: Int,
    val likesCount: Int,
    val viewsCount: Int,
    val createdAt: Long,
)

data class Location(
    val address: String,
    val coordinates: Coordinates,
)

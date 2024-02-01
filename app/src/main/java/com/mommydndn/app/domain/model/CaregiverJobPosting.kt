package com.mommydndn.app.domain.model

import java.util.Date

data class CaregiverJobPosting(
    val id: String,
    val employer: User,
    val title: String,
    val content: String,
    val careTypes: List<CareType>,
    val pay: Pay,
    val workTime: WorkTime,
    val location: Location,
    val preferences: List<CaregiverPreference>,
    val images: List<Image>,
    val applicantsCount: Int,
    val likesCount: Int,
    val viewsCount: Int,
    val createdAt: Date,
    val status: CaregiverJobPostingStatus,
    val isLiked: Boolean,
) {
    constructor() : this(
        id = "",
        employer = User(),
        title = "",
        content = "",
        careTypes = emptyList(),
        pay = Pay(),
        workTime = ShortTermWorkTime(),
        location = Location(),
        preferences = emptyList(),
        images = emptyList(),
        applicantsCount = 0,
        likesCount = 0,
        viewsCount = 0,
        createdAt = Date(),
        status = CaregiverJobPostingStatus.Closed,
        isLiked = false
    )
}

data class Location(
    val address: String,
    val coordinates: Coordinates,
) {
    constructor() : this(
        address = "",
        coordinates = Coordinates(Latitude(0.0), Longitude(0.0))
    )
}

enum class CaregiverJobPostingStatus {
    Open, Closed
}

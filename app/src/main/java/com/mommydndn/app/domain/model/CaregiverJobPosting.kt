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
)

data class Location(
    val address: String,
    val coordinates: Coordinates,
)

enum class CaregiverJobPostingStatus {
    Open, Closed
}

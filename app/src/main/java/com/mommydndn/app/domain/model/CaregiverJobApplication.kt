package com.mommydndn.app.domain.model

import java.util.Date

data class CaregiverJobApplication(
    val id: Long,
    val profileImageUrl: String,
    val nickname: String,
    val availableNeighborhood: AvailableNeighborhoods, // TODO
    val dndnCertified: Boolean,
    val dndnScore: Double,
    val careTypes: List<CareType>,
    val age: Int, // TODO
    val gender: Gender,
    val matchingCount: Int,
    val reviewsCount: Int,
    val responseRate: Int,
    val bio: String,
    val verifications: List<Verification>,
    val preferences: List<CaregiverPreference>,
    val registeredAt: Date,
    val pay: Pay,
)
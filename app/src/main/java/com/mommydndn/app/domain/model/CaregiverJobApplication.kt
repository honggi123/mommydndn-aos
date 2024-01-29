package com.mommydndn.app.domain.model

import java.util.Date

data class CaregiverJobApplication(
    val id: Long,
    val caregiver: User,
    val careTypes: List<CareType>,
    val bio: String,
    val preferences: List<CaregiverPreference>,
    val desiredPay: Pay,
    val registeredAt: Date,
    val isLiked: Boolean,
)
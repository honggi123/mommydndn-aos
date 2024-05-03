package com.mommydndn.app.domain.model

import java.util.Date

data class CaregiverJobApplication(
    val id: String,
    val caregiver: User,
    val careTypes: List<CareType>,
    val bio: String,
    val preferences: List<CaregiverPreference>,
    val desiredPay: Pay,
    val registeredAt: Date,
    val isLiked: Boolean,
) {
    constructor() : this(
        id = "",
        caregiver = User(),
        careTypes = emptyList(),
        bio = "",
        preferences = emptyList(),
        desiredPay = Pay(),
        registeredAt = Date(),
        isLiked = false,
    )
}
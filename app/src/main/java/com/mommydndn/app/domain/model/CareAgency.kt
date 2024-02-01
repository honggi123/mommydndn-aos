package com.mommydndn.app.domain.model

import java.util.Date

data class CareAgency(
    val id: String,
    val coverImages: List<Image>,
    val name: String,
    val profileImage: Image,
    val isDndnCertified: Boolean,
    val availableNeighborhoods: AvailableNeighborhoods,
    val dndnScore: Double,
    val matchingCount: Int,
    val reviewsCount: Int,
    val responseRate: Int,
    val registeredAt: Date,
    val bio: String,
    val minMonthlySalary: Int,
    val maxMonthlySalary: Int,
    val commissionRate: Float,
    val careTypes: List<CareType>,
    val otherConditions: List<CareAgencyOtherCondition>,
    val isLiked: Boolean,
) {
    constructor() : this(
        id = "",
        coverImages = emptyList(),
        name = "",
        profileImage = Image(),
        isDndnCertified = false,
        availableNeighborhoods = AvailableNeighborhoods(),
        dndnScore = 0.0,
        matchingCount = 0,
        reviewsCount = 0,
        responseRate = 0,
        registeredAt = Date(),
        bio = "",
        minMonthlySalary = 0,
        maxMonthlySalary = 0,
        commissionRate = 0F,
        careTypes = emptyList(),
        otherConditions = emptyList(),
        isLiked = false
    )
}

enum class CareAgencyOtherCondition {
    AfterSalesGuranteed
}
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
)

enum class CareAgencyOtherCondition {
    AfterSalesGuranteed
}
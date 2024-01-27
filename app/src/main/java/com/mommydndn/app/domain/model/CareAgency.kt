package com.mommydndn.app.domain.model

import java.util.Date

data class CareAgency(
    val id: String,
    val name: String,
    val profileImage: Image,
    val coverImages: List<Image>,
    val dndnCertified: Boolean,
    val availableNeighborhoods: AvailableNeighborhoods,
    val dndnScore: Double,
    val matchingCount: Int,
    val reviewCount: Int,
    val responseRate: Int,
    val bio: String,
    val registeredAt: Date,
    val minMonthlySalary: Int,
    val maxMonthlySalary: Int,
    val commissionRate: Float,
    val careTypes: List<CareType>,
    val otherConditions: List<CareAgencyOtherCondition>,
)

enum class CareAgencyOtherCondition {
    AfterSalesGuranteed
}
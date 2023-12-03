package com.mommydndn.app.domain.model.care

import java.time.LocalDate
import java.time.LocalDateTime

data class CareJobOpening(
    val id: Int,
    val title: String,
    val careTypes: List<CareType>,
    val createdAt: LocalDateTime,
    val payPeriod: PayPeriod,
    val pay: Int,
    val workPeriod: WorkPeriod,
    val onetimeWorkDates: List<LocalDate>,
    val regularWorkStartDate: LocalDate,
    val regularWorkEndDate: LocalDate,
    val workHours: WorkHours,
    val content: String,
    val tags: List<String>,
    val images: List<Image>,
    val applicantsCount: Int,
    val likesCount: Int,
    val viewsCount: Int,
    val location: Location
)

data class Image(
    val id: Long,
    val url: String
)

data class Location(
    val neighborhoodName: String,
    val address: String,
    val latitude: Double,
    val longitude: Double,
)

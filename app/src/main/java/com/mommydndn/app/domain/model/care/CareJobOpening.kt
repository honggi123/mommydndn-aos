package com.mommydndn.app.domain.model.care

import com.mommydndn.app.domain.model.CareType
import com.mommydndn.app.domain.model.PayPeriod
import com.mommydndn.app.domain.model.User
import com.mommydndn.app.domain.model.WorkHours
import com.mommydndn.app.domain.model.WorkPeriod
import java.time.LocalDate
import java.time.LocalDateTime

data class CareJobOpening(
    val id: Long,
    val employer: User,
    val title: String,
    val careTypes: List<CareType>,
    val createdAt: LocalDateTime,
    val pay: Pay,
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

data class Pay(
    val period: PayPeriod,
    val pay: Int,
)

data class Work(
    val period: WorkPeriod,

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

package com.mommydndn.app.ui.features.care.job.opening.list

import com.mommydndn.app.domain.model.care.CareType
import com.mommydndn.app.domain.model.care.PayPeriod
import com.mommydndn.app.domain.model.care.WorkPeriod
import java.time.DayOfWeek
import java.time.LocalTime
import java.time.ZonedDateTime

data class CareJobOpening(
    val workPeriod: WorkPeriod,
    val careTypes: List<CareType>,
    val isClosed: Boolean,
    val title: String,
    val isLiked: Boolean,
    val neighborhoodName: String,
    val createdAt: ZonedDateTime,
    val daysOfWeek: List<DayOfWeek>,
    val startTime: LocalTime,
    val endTime: LocalTime,
    val payPeriod: PayPeriod,
    val pay: Int,
)

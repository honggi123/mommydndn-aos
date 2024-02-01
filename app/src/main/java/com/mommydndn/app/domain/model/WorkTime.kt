package com.mommydndn.app.domain.model

import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalTime

sealed interface WorkTime

data class ShortTermWorkTime(
    val dates: List<LocalDate>,
    val startTime: LocalTime,
    val endTime: LocalTime,
    val flexible: Boolean,
) : WorkTime {
    constructor() : this(
        dates = emptyList(),
        startTime = LocalTime.now(),
        endTime = LocalTime.now(),
        flexible = false
    )
}

data class RegularWorkTime(
    val daysOfWeek: List<DayOfWeek>,
    val startDate: LocalDate,
    val endDate: LocalDate,
    val startTime: LocalTime,
    val endTime: LocalTime,
    val flexible: Boolean,
) : WorkTime

data class WorkHours(
    val startTime: LocalTime,
    val endTime: LocalTime
)
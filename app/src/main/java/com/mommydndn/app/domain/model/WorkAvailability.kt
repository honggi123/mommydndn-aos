package com.mommydndn.app.domain.model

import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalTime

sealed interface WorkAvailability {

    val startTime: LocalTime
    val endTime: LocalTime

    data class OneTime(
        val dates: List<LocalDate>,
        override val startTime: LocalTime,
        override val endTime: LocalTime,
    ) : WorkAvailability

    data class Regular(
        val daysOfWeek: List<DayOfWeek>,
        val startDate: LocalDate,
        val endDate: LocalDate,
        override val startTime: LocalTime,
        override val endTime: LocalTime,
    ) : WorkAvailability
}
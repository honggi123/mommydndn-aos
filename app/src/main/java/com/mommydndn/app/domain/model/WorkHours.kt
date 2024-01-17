package com.mommydndn.app.domain.model

import java.time.LocalTime

data class WorkHours(
    val start: LocalTime,
    val end: LocalTime
)
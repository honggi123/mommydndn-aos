package com.mommydndn.app.domain.model.care

import java.time.LocalTime

data class WorkHours(
    val start: LocalTime,
    val end: LocalTime
)
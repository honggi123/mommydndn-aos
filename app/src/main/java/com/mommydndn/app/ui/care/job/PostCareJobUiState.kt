package com.mommydndn.app.ui.care.job

import android.net.Uri
import com.mommydndn.app.domain.model.CareType
import com.mommydndn.app.domain.model.OtherOption
import com.mommydndn.app.domain.model.PayPeriod
import com.mommydndn.app.domain.model.WorkPeriod
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalTime

sealed interface PostCareJobOpeningUiState

data class PostCareJobUiModel(
    val title: String,
    val description: String,
    val careTypes: List<CareType>,
    val workPeriod: WorkPeriod,
    val startTime: LocalTime?,
    val endTime: LocalTime?,
    val negotiable: Boolean,
    val selectedDates: List<LocalDate> = emptyList(),
    val selectedDaysOfWeek: List<DayOfWeek> = emptyList(),
    val startDate: LocalDate? = null,
    val endDate: LocalDate? = null,
    val payPeriod: PayPeriod,
    val pay: Int,
    val photoUris: List<Uri>,
    val selectedConditions: List<OtherOption>,
)
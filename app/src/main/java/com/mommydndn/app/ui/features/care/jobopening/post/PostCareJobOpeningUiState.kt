package com.mommydndn.app.ui.features.care.jobopening.post

import android.net.Uri
import com.mommydndn.app.domain.model.care.CareType
import com.mommydndn.app.domain.model.care.OtherCondition
import com.mommydndn.app.domain.model.care.PayPeriod
import com.mommydndn.app.domain.model.care.WorkPeriod
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalTime

sealed interface PostCareJobOpeningUiState {
}

data class JobOpeningUiModel(
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
    val selectedConditions: List<OtherCondition>,
)
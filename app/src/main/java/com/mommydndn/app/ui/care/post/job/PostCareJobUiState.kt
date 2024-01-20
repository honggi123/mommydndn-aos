package com.mommydndn.app.ui.care.post.job

import android.net.Uri
import com.mommydndn.app.domain.model.CareType
import com.mommydndn.app.domain.model.CareWorkerOtherCondition
import com.mommydndn.app.domain.model.PayPeriod
import com.mommydndn.app.domain.model.WorkPeriod
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalTime

sealed interface PostCareJobOpeningUiState

data class PostCareJobUiModel(
    val title: String,
    val content: String,
    val careTypes: List<CareType>,
    val workDateTimes: CareJobWorkDateTimesUiModel,
    val address: String,
    val pay: CareJobPayUiModel,
    val imageUris: List<Uri>,
    val otherConditions: List<CareWorkerOtherCondition>,
)

data class CareJobWorkDateTimesUiModel(
    val workPeriod: WorkPeriod = WorkPeriod.OneTime,
    val oneTimeDates: List<LocalDate> = emptyList(),
    val regularDaysOfWeek: List<DayOfWeek> = emptyList(),
    val regularStartDate: LocalDate? = null,
    val regularEndDate: LocalDate? = null,
    val startTime: LocalTime? = null,
    val endTime: LocalTime? = null,
    val negotiable: Boolean = false,
)

data class CareJobPayUiModel(
    val period: PayPeriod,
    val pay: Int,
)
package com.mommydndn.app.data.network.model.care.response


import com.mommydndn.app.data.model.care.CaringType
import com.mommydndn.app.data.model.care.CaringTypeSerializer
import com.mommydndn.app.data.model.care.SalaryType
import com.mommydndn.app.data.model.care.SalaryTypeSerializer
import com.mommydndn.app.data.model.common.DayOfWeekType
import com.mommydndn.app.data.model.common.DayOfWeekTypeSerializer
import com.mommydndn.app.data.network.model.care.CareTypeApiModel
import com.mommydndn.app.data.network.model.care.DayOfWeekApiModel
import com.mommydndn.app.data.network.model.care.SalaryTypeApiModel
import com.mommydndn.app.data.network.model.common.MetaApiModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetJobOpeningSummaryListResponse(
    @SerialName("jobOfferSummaryList")
    val items: List<JobOpeningSummaryApiModel>,
    val meta: MetaApiModel
)

@Serializable
data class JobOpeningSummaryApiModel(
    @SerialName("jobOfferId")
    val id: Int,
    @SerialName("dateList")
    val oneTimeWorkDateList: List<String>,
    @SerialName("neighborhood")
    val workingNeighborhood: String,
    @SerialName("startDate")
    val regularWorkStartDate: Long?,
    @SerialName("endDate")
    val regularWorkEndDate: Long?,
    @SerialName("startTime")
    val regularWorkStartTime: String?,
    @SerialName("endTime")
    val regularWorkEndTime: String?,
    @SerialName("salaryTypeCode")
    val salaryType: SalaryTypeApiModel,
    @SerialName("days")
    val daysOfWeek: List<DayOfWeekApiModel>,
    @SerialName("caringTypeCodeList")
    val careTypes: List<CareTypeApiModel>,
    val title: String,
    val isClosed: Boolean,
    val isLiked: Boolean,
    val salary: Int,
    val createdAt: Long
)
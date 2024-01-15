package com.mommydndn.app.data.network.service.care.response


import com.mommydndn.app.data.network.model.NetworkCareType
import com.mommydndn.app.data.network.model.NetworkMeta
import com.mommydndn.app.data.network.service.care.model.DayOfWeekApiModel
import com.mommydndn.app.data.network.service.care.model.SalaryTypeApiModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetCareJobSummaryListResponse(
    @SerialName("jobOfferSummaryList")
    val items: List<CareJobSummaryApiModel>,
    val meta: NetworkMeta
)

@Serializable
data class CareJobSummaryApiModel(
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
    val careTypes: List<NetworkCareType>,
    val title: String,
    val isClosed: Boolean,
    val isLiked: Boolean,
    val salary: Int,
    val createdAt: Long
)
package com.mommydndn.app.data.network.service.response

import com.mommydndn.app.data.network.model.NetworkCareJobEmployer
import com.mommydndn.app.data.network.model.NetworkCareType
import com.mommydndn.app.data.network.model.NetworkImage
import com.mommydndn.app.data.network.model.NetworkNeighborhood
import com.mommydndn.app.data.network.model.NetworkPayPeriod
import com.mommydndn.app.data.network.model.NetworkWorkPeriod
import com.mommydndn.app.data.network.model.NetworkWorkerOtherCondition
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.time.DayOfWeek

@Serializable
data class GetCareJobOpeningDetailsResponse(
    @SerialName("jobOfferId")
    val id: Long,
    @SerialName("emd")
    val neighborhood: NetworkNeighborhood,
    val latitude: Double,
    val longitude: Double,
    val title: String,
    @SerialName("caringTypeCodeList")
    val careTypes: List<NetworkCareType>,
    val createdAt: Long,
    @SerialName("salaryTypeCode")
    val payPeriod: NetworkPayPeriod,
    @SerialName("salary")
    val pay: Int?,
    @SerialName("taskTypeCode")
    val workPeriod: NetworkWorkPeriod,
    @SerialName("dateList")
    val oneTimeWorkDates: List<String>,
    @SerialName("days")
    val daysOfWeek: List<DayOfWeek>?,
    @SerialName("startTime")
    val regularWorkStartTime: String?,
    @SerialName("endTime")
    val regularWorkEndTime: String?,
    @SerialName("startDate")
    val regularWorkStartDate: Long?,
    @SerialName("endDate")
    val regularWorkEndDate: Long?,
    val content: String,
    @SerialName("indOtherConditionCodeList")
    val otherConditions: List<NetworkWorkerOtherCondition>,
    @SerialName("imageList")
    val images: List<NetworkImage>,
    val applicantCount: Int,
    val likeCount: Int,
    @SerialName("hits")
    val views: Int,
    val isClosed: Boolean,
    val isLiked: Boolean,
    @SerialName("jobOfferAuthor")
    val employer: NetworkCareJobEmployer,
)
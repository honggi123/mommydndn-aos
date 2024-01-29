package com.mommydndn.app.data.network.service.request

import com.mommydndn.app.data.network.model.NetworkCareType
import com.mommydndn.app.data.network.model.NetworkPayPeriod
import com.mommydndn.app.data.network.model.NetworkWorkPeriod
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.time.DayOfWeek

@Serializable
data class PostCareJobOpeningRequest(
    val title: String,
    val content: String,
    @SerialName("caringTypeCodeList")
    val careTypes: List<NetworkCareType>, // TODO: SERIALIZER
    @SerialName("taskTypeCode")
    val workPeriodType: NetworkWorkPeriod,
    @SerialName("dateList")
    val oneTimeWorkDates: List<Long>?, // TODO: LONG -> XYZ_DATE, TIME
    @SerialName("days")
    val regularWorkDaysOfWeek: List<DayOfWeek>?,
    @SerialName("startDate")
    val regularWorkStartDate: Long?,
    @SerialName("endDate")
    val regularWorkEndDate: Long?,
    @SerialName("startTime")
    val regularWorkStartTime: String?,
    @SerialName("endTime")
    val regularWorkEndTime: String?,
    val latitude: Double,
    val longitude: Double,
    @SerialName("salaryTypeCode")
    val payPeriod: NetworkPayPeriod,
    @SerialName("salary")
    val pay: Int?,
    @SerialName("imageIdList")
    val imageIds: List<Int>,
    @SerialName("indOtherConditionIdList")
    val individualOtherConditionIds: List<Int>,
)
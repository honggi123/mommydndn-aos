package com.mommydndn.app.data.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.time.DayOfWeek

@Serializable
data class NetworkCareJob(
    @SerialName("jobOfferId")
    val id: Long,
    @SerialName("taskTypeCode")
    val workPeriod: NetworkWorkPeriod,
    @SerialName("caringTypeCodeList")
    val careTypes: List<NetworkCareType>,
    val title: String,
    val neighborhood: String,
    val createdAt: Long,
    @SerialName("dateList")
    val oneTimeWorkDates: List<String>,
    @SerialName("days")
    val daysOfWeek: List<DayOfWeek>,
    val startTime: String?,
    val endTime: String?,
    @SerialName("startDate")
    val regularWorkStartDate: Long?,
    @SerialName("endDate")
    val regularWorkEndDate: Long?,
    @SerialName("salaryTypeCode")
    val payPeriod: NetworkPayPeriod,
    @SerialName("salary")
    val pay: Int?,
    val isClosed: Boolean,
    val isLiked: Boolean,
)
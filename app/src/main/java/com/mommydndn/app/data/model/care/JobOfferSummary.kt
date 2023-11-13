package com.mommydndn.app.data.model.care


import com.mommydndn.app.data.model.common.DayOfWeekType
import com.mommydndn.app.data.model.common.DayOfWeekTypeSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class JobOfferSummary(
    @SerialName("jobOfferSummaryList")
    val jobOfferSummaryList: List<JobOfferSummaryListItem>,
    @SerialName("meta")
    val meta: Meta
)

@Serializable
data class JobOfferSummaryListItem(
    @SerialName("caringTypeCodeList")
    val caringTypeCodeList: List<@Serializable(with = CaringTypeSerializer::class) CaringType>,
    @SerialName("createdAt")
    val createdAt: Long,
    @SerialName("dateList")
    val dateList: List<Long>,
    @SerialName("days")
    val days: List<@Serializable(with = DayOfWeekTypeSerializer::class) DayOfWeekType>,
    @SerialName("endDate")
    val endDate: Long,
    @SerialName("endTime")
    val endTime: String?,
    @SerialName("isClosed")
    val isClosed: Boolean,
    @SerialName("isLiked")
    val isLiked: Boolean,
    @SerialName("jobOfferId")
    val jobOfferId: Int,
    @SerialName("neighborhood")
    val neighborhood: String,
    @SerialName("salary")
    val salary: Int,
    @Serializable(with = SalaryTypeSerializer::class)
    @SerialName("salaryTypeCode")
    val salaryTypeCode: SalaryType,
    @SerialName("startDate")
    val startDate: Long,
    @SerialName("startTime")
    val startTime: String?,
    @SerialName("title")
    val title: String
)

@Serializable
data class Meta(
    @SerialName("currentPageNum")
    val currentPageNum: Int,
    @SerialName("requestTimestamp")
    val requestTimestamp: Long,
    @SerialName("totalCount")
    val totalCount: Int
)
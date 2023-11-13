package com.mommydndn.app.data.api.model.request


import com.mommydndn.app.data.model.care.CaringType
import com.mommydndn.app.data.model.care.CaringTypeSerializer
import com.mommydndn.app.data.model.care.SortingType
import com.mommydndn.app.data.model.care.WorkPeriodType
import com.mommydndn.app.data.model.care.WorkPeriodTypeSerializer
import com.mommydndn.app.data.model.common.DayOfWeekType
import com.mommydndn.app.data.model.common.DayOfWeekTypeSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class JobOfferListRequest(
    @SerialName("caringTypeCodeList")
    val caringTypeCodeList: List<@Serializable(with = CaringTypeSerializer::class) CaringType>,
    @SerialName("days")
    val days: List<@Serializable(with = DayOfWeekTypeSerializer::class) DayOfWeekType>,
    @SerialName("emdId")
    val emdId: Int,
    @SerialName("endTime")
    val endTime: String?,
    @SerialName("keyword")
    val keyword: String?,
    @SerialName("neighborhoodScope")
    val neighborhoodScope: Int,
    @SerialName("paginationRequest")
    val paginationRequest: PaginationRequest,
    @SerialName("sortingCondition")
    val sortingCondition: SortingType,
    @SerialName("startTime")
    val startTime: String?,
    @SerialName("taskTypeCodeList")
    val taskTypeCodeList: List<@Serializable(with = WorkPeriodTypeSerializer::class) WorkPeriodType>
)

@Serializable
data class PaginationRequest(
    @SerialName("pageNum")
    val pageNum: Int,
    @SerialName("pageSize")
    val pageSize: Int,
    @SerialName("requestTimestamp")
    val requestTimestamp: Long
)
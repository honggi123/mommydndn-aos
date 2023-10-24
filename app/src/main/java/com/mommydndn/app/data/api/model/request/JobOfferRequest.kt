package com.mommydndn.app.data.api.model.request

import com.mommydndn.app.data.model.care.SalaryType
import com.mommydndn.app.data.model.common.DayOfWeekType
import com.mommydndn.app.data.model.map.EmdItem
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
@Serializable
data class JobOfferRequest(
    @SerialName("title") val title: String,
    @SerialName("content") val content: String,
    @SerialName("caringTypeIdList") val caringTypeIdList: List<Int>,
    @SerialName("taskTypeCode") val taskTypeCode: String,
    @SerialName("startDate") val startDate: Long?,
    @SerialName("endDate") val endDate: Long?,
    @SerialName("days") val days: List<DayOfWeekType>?,
    @SerialName("startTime") val startTime: Long?,
    @SerialName("endTime") val endTime: Long?,
    @SerialName("emd") val emd: EmdItem,
    @SerialName("latitude") val latitude: Double,
    @SerialName("longitude") val longitude: Double,
    @SerialName("salaryTypeCode") val salaryTypeCode: SalaryType,
    @SerialName("salary") val salary: Int?,
    @SerialName("indOtherConditionIdList") val indOtherConditionIdList: List<Int>,
    @SerialName("imageIdList") val imageIdList: List<Int>
)
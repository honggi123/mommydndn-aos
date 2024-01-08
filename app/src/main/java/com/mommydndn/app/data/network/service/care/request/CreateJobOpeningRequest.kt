package com.mommydndn.app.data.network.service.care.request

import com.mommydndn.app.data.network.service.care.model.CareTypeApiModel
import com.mommydndn.app.data.network.service.care.model.DayOfWeekApiModel
import com.mommydndn.app.data.network.service.care.model.SalaryTypeApiModel
import com.mommydndn.app.data.network.service.care.model.WorkPeriodTypeApiModel
import com.mommydndn.app.data.network.service.common.model.LocationApiModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreateJobOpeningRequest(
    @SerialName("dateList")
    val oneTimeWorkDates: List<Long>?,
    @SerialName("startDate")
    val regularWorkStartDate: Long?,
    @SerialName("endDate")
    val regularWorkEndDate: Long?,
    @SerialName("startTime")
    val regularWorkStartTime: String?,
    @SerialName("endTime")
    val regularWorkEndTime: String?,
    @SerialName("emd")
    val workingNeighborhood: LocationApiModel,
    @SerialName("indOtherConditionIdList")
    val individualOtherConditionIdList: List<Int>,
    @SerialName("taskTypeCode")
    val workPeriodType: WorkPeriodTypeApiModel,
    @SerialName("days")
    val oneTimeWorkDaysOfWeek: List<DayOfWeekApiModel>?,
    @SerialName("caringTypeCodeList")
    val careTypes: List<CareTypeApiModel>,
    @SerialName("salaryTypeCode")
    val salaryType: SalaryTypeApiModel,
    val title: String,
    val content: String,
    val imageIdList: List<Int>,
    val salary: Int?,
    val latitude: Double,
    val longitude: Double
)
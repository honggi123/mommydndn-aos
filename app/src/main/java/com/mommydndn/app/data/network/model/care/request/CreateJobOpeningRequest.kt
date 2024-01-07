package com.mommydndn.app.data.network.model.care.request

import com.mommydndn.app.data.model.care.CaringType
import com.mommydndn.app.data.model.care.CaringTypeSerializer
import com.mommydndn.app.data.model.care.SalaryType
import com.mommydndn.app.data.model.care.SalaryTypeSerializer
import com.mommydndn.app.data.model.care.WorkPeriodType
import com.mommydndn.app.data.model.care.WorkPeriodTypeSerializer
import com.mommydndn.app.data.model.common.DayOfWeekType
import com.mommydndn.app.data.model.common.DayOfWeekTypeSerializer
import com.mommydndn.app.data.network.model.care.CareTypeApiModel
import com.mommydndn.app.data.network.model.care.DayOfWeekApiModel
import com.mommydndn.app.data.network.model.care.SalaryTypeApiModel
import com.mommydndn.app.data.network.model.care.WorkPeriodTypeApiModel
import com.mommydndn.app.data.network.model.common.LocationApiModel
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
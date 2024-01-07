package com.mommydndn.app.data.network.model.care.request


import com.mommydndn.app.data.model.care.CaringType
import com.mommydndn.app.data.model.care.CaringTypeSerializer
import com.mommydndn.app.data.model.care.SortingType
import com.mommydndn.app.data.model.care.WorkPeriodType
import com.mommydndn.app.data.model.care.WorkPeriodTypeSerializer
import com.mommydndn.app.data.model.common.DayOfWeekType
import com.mommydndn.app.data.model.common.DayOfWeekTypeSerializer
import com.mommydndn.app.data.network.model.care.CareTypeApiModel
import com.mommydndn.app.data.network.model.care.DayOfWeekApiModel
import com.mommydndn.app.data.network.model.care.SortingTypeApiModel
import com.mommydndn.app.data.network.model.care.WorkPeriodTypeApiModel
import com.mommydndn.app.data.network.model.common.PaginationApiModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

typealias GetJobOpeningListRequest = List<JobOpeningRequestApiModel>

@Serializable
data class JobOpeningRequestApiModel(
    @SerialName("emdId")
    val currentNeighborhhoodId: Int,
    @SerialName("startTime")
    val startTime: String?,
    @SerialName("endTime")
    val endTime: String?,
    @SerialName("paginationRequest")
    val pageMeta: PaginationApiModel,
    @SerialName("caringTypeCodeList")
    val careTypes: List<CareTypeApiModel>,
    @SerialName("days")
    val daysOfWeek: List<DayOfWeekApiModel>,
    @SerialName("taskTypeCodeList")
    val workPeriodTypes: List<WorkPeriodTypeApiModel>,
    @SerialName("sortingCondition")
    val sortingType: SortingTypeApiModel,
    val keyword: String?,
    val neighborhoodScope: Int
)


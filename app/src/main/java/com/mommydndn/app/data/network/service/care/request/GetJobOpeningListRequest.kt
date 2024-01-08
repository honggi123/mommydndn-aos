package com.mommydndn.app.data.network.service.care.request


import com.mommydndn.app.data.network.service.care.model.CareTypeApiModel
import com.mommydndn.app.data.network.service.care.model.DayOfWeekApiModel
import com.mommydndn.app.data.network.service.care.model.SortingTypeApiModel
import com.mommydndn.app.data.network.service.care.model.WorkPeriodTypeApiModel
import com.mommydndn.app.data.network.service.common.model.PaginationApiModel
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


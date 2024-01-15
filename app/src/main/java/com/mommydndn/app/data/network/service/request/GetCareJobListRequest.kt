package com.mommydndn.app.data.network.service.request


import com.mommydndn.app.data.network.model.NetworkCareType
import com.mommydndn.app.data.network.model.NetworkPage
import com.mommydndn.app.data.network.service.care.model.DayOfWeekApiModel
import com.mommydndn.app.data.network.service.care.model.SortingTypeApiModel
import com.mommydndn.app.data.network.service.care.model.WorkPeriodTypeApiModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

typealias GetCareJobListRequest = List<CareJobRequestApiModel>

@Serializable
data class CareJobRequestApiModel(
    @SerialName("emdId")
    val locationId: Int,
    @SerialName("paginationRequest")
    val pageMeta: NetworkPage,
    @SerialName("caringTypeCodeList")
    val careTypes: List<NetworkCareType>,
    @SerialName("days")
    val daysOfWeek: List<DayOfWeekApiModel>,
    @SerialName("taskTypeCodeList")
    val workPeriodTypes: List<WorkPeriodTypeApiModel>,
    @SerialName("sortingCondition")
    val sortingType: SortingTypeApiModel,
    val keyword: String?,
    val startTime: String?,
    val endTime: String?,
    val neighborhoodScope: Int
)


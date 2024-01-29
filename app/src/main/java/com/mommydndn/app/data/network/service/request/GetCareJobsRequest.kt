package com.mommydndn.app.data.network.service.request

import com.mommydndn.app.data.network.model.NetworkCareType
import com.mommydndn.app.data.network.model.NetworkPage
import com.mommydndn.app.data.network.model.NetworkSortBy
import com.mommydndn.app.data.network.model.NetworkWorkPeriod
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.time.DayOfWeek

@Serializable
data class GetCareJobsRequest(
    @SerialName("paginationRequest")
    val pageMeta: NetworkPage,
    val keyword: String?,
    @SerialName("sortingCondition")
    val sortBy: NetworkSortBy,
    @SerialName("emdId")
    val neighborhoodId: Long,
    val neighborhoodScope: Int,
    @SerialName("caringTypeCodeList")
    val careTypes: List<NetworkCareType>?,
    @SerialName("days")
    val daysOfWeek: List<DayOfWeek>?,
    // TODO: TIME + SERIALIZER
    val startTime: String?,
    val endTime: String?,
    @SerialName("taskTypeCodeList")
    val workPeriods: List<NetworkWorkPeriod>?,
)


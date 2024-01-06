package com.mommydndn.app.data.network.model.care.request

import com.mommydndn.app.data.model.care.SortingType
import com.mommydndn.app.data.network.model.care.response.CareTypeSerializer
import com.mommydndn.app.data.network.model.care.response.DayOfWeekSerializer
import com.mommydndn.app.data.network.model.care.response.WorkPeriodSerializer
import com.mommydndn.app.domain.model.care.CareType
import com.mommydndn.app.domain.model.care.WorkPeriod
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.time.DayOfWeek

// todo: order + filter?
@Serializable
data class GetCareJobOpeningListRequest(
    @SerialName("paginationRequest")
    val pageMeta: PageMeta,
    @SerialName("keyword")
    val keyword: String?,
    @SerialName("emdId") // ?
    val neighborhoodId: Int,
    @SerialName("neighborhoodScope")
    val nearbyNeighborhoodDistance: Int,
    @SerialName("caringTypeCodeList")
    val careTypes: List<@Serializable(with = CareTypeSerializer::class) CareType>,
    @SerialName("days")
    val daysOfWeek: List<@Serializable(with = DayOfWeekSerializer::class) DayOfWeek>,
    @SerialName("sortingCondition")
    val orderBy: SortingType,
    @SerialName("startTime")
    val startTime: String?,
    @SerialName("endTime")
    val endTime: String?,
    @SerialName("taskTypeCodeList")
    val workPeriods: List<@Serializable(with = WorkPeriodSerializer::class) WorkPeriod>
)

@Serializable
data class PageMeta(
    @SerialName("pageNum")
    val page: Int,
    @SerialName("pageSize")
    val size: Int,
    @SerialName("requestTimestamp")
    val requestedAt: Long,
)
package com.mommydndn.app.data.network.model.care.request


import com.mommydndn.app.data.model.care.CaringType
import com.mommydndn.app.data.model.care.CaringTypeSerializer
import com.mommydndn.app.data.model.care.SortingType
import com.mommydndn.app.data.model.care.WorkPeriodType
import com.mommydndn.app.data.model.care.WorkPeriodTypeSerializer
import com.mommydndn.app.data.model.common.DayOfWeekType
import com.mommydndn.app.data.model.common.DayOfWeekTypeSerializer
import com.mommydndn.app.data.network.model.common.PaginationApiModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

typealias GetJobOpeningListRequest = List<GetJobOpeningRequest>

@Serializable
data class GetJobOpeningRequest(
    @SerialName("emdId")
    val currentNeighborhhoodId: Int,
    @SerialName("sortingCondition")
    val sortingType: SortingType,
    @SerialName("startTime")
    val startTime: String?,
    @SerialName("endTime")
    val endTime: String?,
    @SerialName("paginationRequest")
    val pageMeta: PaginationApiModel,
    @SerialName("caringTypeCodeList")
    val careTypes: List<@Serializable(with = CaringTypeSerializer::class) CaringType>,
    @SerialName("days")
    val daysOfWeek: List<@Serializable(with = DayOfWeekTypeSerializer::class) DayOfWeekType>,
    @SerialName("taskTypeCodeList")
    val workPeriodTypes: List<@Serializable(with = WorkPeriodTypeSerializer::class) WorkPeriodType>,
    val keyword: String?,
    val neighborhoodScope: Int
)


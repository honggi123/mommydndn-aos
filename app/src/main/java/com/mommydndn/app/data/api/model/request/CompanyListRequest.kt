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
data class CompanyListRequest(
    @SerialName("keyword")
    val keyword: String?,
    @SerialName("paginationRequest")
    val paginationRequest: PaginationRequest,
    @SerialName("sortingCondition")
    val sortingCondition: SortingType,
    @SerialName("emdId")
    val emdId: Int,
    @SerialName("neighborhoodScope")
    val neighborhoodScope: Int,
    @SerialName("caringTypeCodeList")
    val caringTypeCodeList: List<@Serializable(with = CaringTypeSerializer::class) CaringType>,
    @SerialName("minMonthlySalary")
    val minMonthlySalary: Int?,
    @SerialName("maxMonthlySalary")
    val maxMonthlySalary: Int?
)

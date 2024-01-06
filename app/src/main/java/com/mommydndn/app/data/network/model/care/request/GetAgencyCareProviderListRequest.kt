package com.mommydndn.app.data.network.model.care.request

import com.mommydndn.app.data.model.care.CaringType
import com.mommydndn.app.data.model.care.CaringTypeSerializer
import com.mommydndn.app.data.model.care.SortingType
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetAgencyCareProviderListRequest(
    @SerialName("keyword")
    val keyword: String?,
    @SerialName("paginationRequest")
    val paginationRequest: AgencyCareProviderPaginationRequest,
    @SerialName("sortingCondition")
    val sortingType: SortingType,
    @SerialName("emdId")
    val emdId: Int,
    @SerialName("neighborhoodScope")
    val neighborhoodScope: Int,
    @SerialName("caringTypeCodeList")
    val careTypeList: List<@Serializable(with = CaringTypeSerializer::class) CaringType>,
    @SerialName("minMonthlySalary")
    val minMonthlySalary: Int?,
    @SerialName("maxMonthlySalary")
    val maxMonthlySalary: Int?
)

@Serializable
data class AgencyCareProviderPaginationRequest(
    @SerialName("pageNum")
    val pageNum: Int,
    @SerialName("pageSize")
    val pageSize: Int,
    @SerialName("requestTimestamp")
    val requestTimestamp: Long
)
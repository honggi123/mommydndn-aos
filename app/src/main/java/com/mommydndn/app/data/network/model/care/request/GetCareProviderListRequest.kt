package com.mommydndn.app.data.network.model.care.request

import com.mommydndn.app.data.model.care.CaringType
import com.mommydndn.app.data.model.care.CaringTypeSerializer
import com.mommydndn.app.data.model.care.SortingType
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

typealias GetCareProviderListRequest = List<GetCareProviderRequest>

@Serializable
data class GetCareProviderRequest(
    @SerialName("caringTypeCodeList")
    val caringTypeCodeList: List<@Serializable(with = CaringTypeSerializer::class) CaringType>,
    @SerialName("emdId")
    val emdId: Int,
    @SerialName("neighborhoodScope")
    val neighborhoodScope: Int,
    @SerialName("keyword")
    val keyword: String?,
    @SerialName("paginationRequest")
    val paginationRequest: GetCareProviderRequestPaginationRequest,
    @SerialName("sortingCondition")
    val sortingCondition: SortingType
)

@Serializable
data class GetCareProviderRequestPaginationRequest(
    @SerialName("pageNum")
    val pageNum: Int,
    @SerialName("pageSize")
    val pageSize: Int,
    @SerialName("requestTimestamp")
    val requestTimestamp: Long
)
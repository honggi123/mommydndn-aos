package com.mommydndn.app.data.network.model.request

import com.mommydndn.app.data.model.care.CaringType
import com.mommydndn.app.data.model.care.CaringTypeSerializer
import com.mommydndn.app.data.model.care.SortingType
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class JobSeekerListRequest(
    @SerialName("caringTypeCodeList")
    val caringTypeCodeList: List<@Serializable(with = CaringTypeSerializer::class) CaringType>,
    @SerialName("emdId")
    val emdId: Int,
    @SerialName("neighborhoodScope")
    val neighborhoodScope: Int,
    @SerialName("keyword")
    val keyword: String?,
    @SerialName("paginationRequest")
    val paginationRequest: PaginationRequest,
    @SerialName("sortingCondition")
    val sortingCondition: SortingType
)

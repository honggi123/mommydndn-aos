package com.mommydndn.app.data.network.model.care.request

import com.mommydndn.app.data.model.care.CaringType
import com.mommydndn.app.data.model.care.CaringTypeSerializer
import com.mommydndn.app.data.model.care.SortingType
import com.mommydndn.app.data.network.model.common.PaginationApiModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

typealias GetCareProviderListRequest = List<GetCareProviderRequest>

@Serializable
data class GetCareProviderRequest(
    @SerialName("emdId")
    val locationId: Int, // TODO
    @SerialName("neighborhoodScope")
    val neighborhoodScope: Int,
    @SerialName("sortingCondition")
    val sortingType: SortingType,
    @SerialName("paginationRequest")
    val pageMeta: PaginationApiModel,
    @SerialName("caringTypeCodeList")
    val careTypeList: List<@Serializable(with = CaringTypeSerializer::class) CaringType>,
    val keyword: String?
)

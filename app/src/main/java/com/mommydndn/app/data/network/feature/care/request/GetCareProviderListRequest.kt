package com.mommydndn.app.data.network.feature.care.request

import com.mommydndn.app.data.model.care.SortingType
import com.mommydndn.app.data.network.feature.care.model.CareTypeApiModel
import com.mommydndn.app.data.network.feature.common.model.PaginationApiModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

typealias GetCareProviderListRequest = List<com.mommydndn.app.data.network.feature.care.request.CareProviderRequestApiModel>

@Serializable
data class CareProviderRequestApiModel(
    @SerialName("emdId")
    val locationId: Int, // TODO
    @SerialName("neighborhoodScope")
    val neighborhoodScope: Int,
    @SerialName("sortingCondition")
    val sortingType: SortingType,
    @SerialName("paginationRequest")
    val pageMeta: PaginationApiModel,
    @SerialName("caringTypeCodeList")
    val careTypeList: List<CareTypeApiModel>,
    val keyword: String?
)

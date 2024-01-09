package com.mommydndn.app.data.network.service.care.request

import com.mommydndn.app.data.model.care.SortingType
import com.mommydndn.app.data.network.service.care.model.CareTypeApiModel
import com.mommydndn.app.data.network.service.common.model.PaginationApiModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetAgencyCareProviderListRequest(
    @SerialName("emdId")
    val locationId: Int,
    @SerialName("neighborhoodScope")
    val neighborhoodScope: Int,
    @SerialName("paginationRequest")
    val pageMeta: PaginationApiModel,
    @SerialName("sortingCondition")
    val sortingType: SortingType,
    @SerialName("caringTypeCodeList")
    val careTypes: List<CareTypeApiModel>,
    val keyword: String?
)


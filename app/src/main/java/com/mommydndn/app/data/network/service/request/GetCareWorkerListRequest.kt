package com.mommydndn.app.data.network.service.request

import com.mommydndn.app.data.model.care.SortingType
import com.mommydndn.app.data.network.model.NetworkCareType
import com.mommydndn.app.data.network.model.NetworkPage
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

typealias GetCareWorkerListRequest = List<CareWorkerRequestApiModel>

@Serializable
data class CareWorkerRequestApiModel(
    @SerialName("emdId")
    val locationId: Int,
    @SerialName("neighborhoodScope")
    val neighborhoodScope: Int,
    @SerialName("sortingCondition")
    val sortingType: SortingType,
    @SerialName("paginationRequest")
    val pageMeta: NetworkPage,
    @SerialName("caringTypeCodeList")
    val careTypes: List<NetworkCareType>,
    val keyword: String?
)

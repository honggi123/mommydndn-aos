package com.mommydndn.app.data.network.service.request

import com.mommydndn.app.data.model.care.SortingType
import com.mommydndn.app.data.network.model.NetworkCareType
import com.mommydndn.app.data.network.model.NetworkPage
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetAgencyCareWorkerListRequest(
    @SerialName("emdId")
    val locationId: Int,
    @SerialName("neighborhoodScope")
    val neighborhoodScope: Int,
    @SerialName("paginationRequest")
    val pageMeta: NetworkPage,
    @SerialName("sortingCondition")
    val sortingType: SortingType,
    @SerialName("caringTypeCodeList")
    val careTypes: List<NetworkCareType>,
    val keyword: String?
)


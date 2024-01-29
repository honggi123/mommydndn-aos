package com.mommydndn.app.data.network.service.request

import com.mommydndn.app.data.network.model.NetworkCareType
import com.mommydndn.app.data.network.model.NetworkPage
import com.mommydndn.app.data.network.model.NetworkSortBy
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetCareAgenciesRequest(
    @SerialName("paginationRequest")
    val pageMeta: NetworkPage,
    val keyword: String?,
    @SerialName("sortingCondition")
    val sortBy: NetworkSortBy,
    @SerialName("emdId")
    val neighborhoodId: Long,
    val neighborhoodScope: Int,
    @SerialName("caringTypeCodeList")
    val careTypes: List<NetworkCareType>,
)
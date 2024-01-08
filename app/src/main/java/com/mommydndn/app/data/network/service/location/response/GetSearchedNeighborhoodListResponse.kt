package com.mommydndn.app.data.network.service.location.response

import com.mommydndn.app.data.network.service.common.model.LocationApiModel
import com.mommydndn.app.data.network.service.common.model.MetaApiModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetSearchedNeighborhoodListResponse(
    @SerialName("emdList")
    val items: List<LocationApiModel>,
    val meta: MetaApiModel
)
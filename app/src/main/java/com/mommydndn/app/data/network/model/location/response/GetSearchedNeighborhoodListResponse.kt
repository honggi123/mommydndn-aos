package com.mommydndn.app.data.network.model.location.response

import com.mommydndn.app.data.network.model.common.LocationApiModel
import com.mommydndn.app.data.network.model.common.MetaApiModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetSearchedNeighborhoodListResponse(
    @SerialName("emdList")
    val items: List<LocationApiModel>,
    val meta: MetaApiModel
)
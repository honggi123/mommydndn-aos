package com.mommydndn.app.data.network.service.location.response

import com.mommydndn.app.data.network.service.location.model.LocationApiModel
import com.mommydndn.app.data.network.service.common.model.MetaApiModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetNearestNeighborhoodListResponse(
    @SerialName("emdList")
    val items: List<LocationApiModel>,
    val meta: MetaApiModel
)

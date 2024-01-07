package com.mommydndn.app.data.network.model.location.response

import com.mommydndn.app.data.network.model.common.LocationApiModel
import com.mommydndn.app.data.network.model.common.MetaApiModel
import com.mommydndn.app.domain.model.location.Neighborhood
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetNearestNeighborhoodListResponse(
    @SerialName("emdList")
    val items: List<LocationApiModel>,
    val meta: MetaApiModel
)

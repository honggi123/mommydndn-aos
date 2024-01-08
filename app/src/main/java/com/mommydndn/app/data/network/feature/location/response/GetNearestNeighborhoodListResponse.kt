package com.mommydndn.app.data.network.feature.location.response

import com.mommydndn.app.data.network.feature.common.model.LocationApiModel
import com.mommydndn.app.data.network.feature.common.model.MetaApiModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetNearestNeighborhoodListResponse(
    @SerialName("emdList")
    val items: List<LocationApiModel>,
    val meta: MetaApiModel
)

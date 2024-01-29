package com.mommydndn.app.data.network.service.response

import com.mommydndn.app.data.network.model.NetworkMeta
import com.mommydndn.app.data.network.model.NetworkNeighborhood
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetNearestNeighborhoodsResponse(
    val meta: NetworkMeta,
    @SerialName("emdList") val items: List<NetworkNeighborhood>,
)

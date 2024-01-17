package com.mommydndn.app.data.network.service.response

import com.mommydndn.app.data.network.model.NetworkCareAgency
import com.mommydndn.app.data.network.model.NetworkMeta
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetCareAgenciesResponse(
    @SerialName("companySummaryList")
    val items: List<NetworkCareAgency>,
    @SerialName("meta")
    val meta: NetworkMeta,
)



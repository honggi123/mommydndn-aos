package com.mommydndn.app.data.network.service.response

import com.mommydndn.app.data.network.model.NetworkCareJobOpening
import com.mommydndn.app.data.network.model.NetworkMeta
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetCareJobOpeningsResponse(
    @SerialName("jobOfferSummaryList")
    val items: List<NetworkCareJobOpening>,
    val meta: NetworkMeta,
)
package com.mommydndn.app.data.network.service.response

import com.mommydndn.app.data.network.model.NetworkCareJob
import com.mommydndn.app.data.network.model.NetworkMeta
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetCareJobsResponse(
    @SerialName("jobOfferSummaryList")
    val items: List<NetworkCareJob>,
    val meta: NetworkMeta,
)
package com.mommydndn.app.data.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NetworkPage(
    @SerialName("pageNum")
    val page: Int,
    @SerialName("pageSize")
    val size: Int,
    @SerialName("requestTimestamp")
    val requestedAt: Long
)
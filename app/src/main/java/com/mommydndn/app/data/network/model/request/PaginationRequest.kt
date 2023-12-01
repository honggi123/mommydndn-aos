package com.mommydndn.app.data.network.model.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PaginationRequest(
    @SerialName("pageNum")
    val pageNum: Int,
    @SerialName("pageSize")
    val pageSize: Int,
    @SerialName("requestTimestamp")
    val requestTimestamp: Long
)
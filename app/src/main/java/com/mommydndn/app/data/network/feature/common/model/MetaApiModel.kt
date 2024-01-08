package com.mommydndn.app.data.network.feature.common.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MetaApiModel(
    @SerialName("totalCount")
    val totalCount: Long,
    @SerialName("currentPageNum")
    val page: Int,
    @SerialName("requestTimestamp")
    val requestedAt: Long,
)

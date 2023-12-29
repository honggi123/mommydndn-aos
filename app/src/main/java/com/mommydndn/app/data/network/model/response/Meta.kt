package com.mommydndn.app.data.network.model.response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Meta(
    @SerialName("currentPageNum")
    val currentPageNum: Int,
    @SerialName("totalCount")
    val totalCount: Int,
    @SerialName("requestTimestamp")
    val requestTimestamp: Long,
)
package com.mommydndn.app.data.network.model.location.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetLocationResponse(
    @SerialName("emdList")
    val emdList: List<EmdItemResponse>,
    @SerialName("meta")
    val meta: LocationMeta
)

@Serializable
data class LocationMeta(
    @SerialName("currentPageNum")
    val currentPageNum: Int,
    @SerialName("totalCount")
    val totalCount: Int,
    @SerialName("requestTimestamp")
    val requestTimestamp: Long,
)
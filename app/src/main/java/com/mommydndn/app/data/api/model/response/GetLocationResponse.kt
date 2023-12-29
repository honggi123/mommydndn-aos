package com.mommydndn.app.data.api.model.response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetLocationResponse(
    @SerialName("emdList")
    val emdList: List<GetEmdItemResponse>,
    @SerialName("meta")
    val meta: Meta
)
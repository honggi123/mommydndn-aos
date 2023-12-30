package com.mommydndn.app.data.network.model.response


import com.mommydndn.app.data.api.model.response.GetEmdItemResponse
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetLocationResponse(
    @SerialName("emdList")
    val emdList: List<GetEmdItemResponse>,
    @SerialName("meta")
    val meta: Meta
)
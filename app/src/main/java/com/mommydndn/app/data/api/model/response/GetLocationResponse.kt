package com.mommydndn.app.data.api.model.response


import com.mommydndn.app.data.model.map.EmdItem
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetLocationResponse(
    @SerialName("emdList")
    val emdList: List<EmdItem>,
    @SerialName("meta")
    val meta: Meta
)
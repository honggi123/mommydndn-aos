package com.mommydndn.app.data.api.model


import com.mommydndn.app.data.model.EmdItem
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LocationSearchResponse(
    @SerialName("emdList")
    val emdList: List<EmdItem>,
    @SerialName("meta")
    val meta: Meta
)
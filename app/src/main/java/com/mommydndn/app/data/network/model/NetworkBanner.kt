package com.mommydndn.app.data.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NetworkBanner(
    @SerialName("bannerId")
    val id: Int,
    @SerialName("url")
    val imageUrl: String?,
    @SerialName("targetUrl")
    val url: String?,
)
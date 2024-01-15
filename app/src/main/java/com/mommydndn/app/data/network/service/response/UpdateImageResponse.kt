package com.mommydndn.app.data.network.service.response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UpdateImageResponse(
    @SerialName("imageId") val id: Int,
    val url: String,
)
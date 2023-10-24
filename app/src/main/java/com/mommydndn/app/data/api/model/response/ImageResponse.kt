package com.mommydndn.app.data.api.model.response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ImageResponse(
    @SerialName("imageId")
    val imageId: Int,
    @SerialName("url")
    val url: String
)
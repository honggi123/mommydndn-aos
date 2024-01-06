package com.mommydndn.app.data.network.model.common


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetImageIdResponse(
    @SerialName("imageId")
    val id: Int,
    val url: String
)
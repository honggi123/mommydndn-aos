package com.mommydndn.app.data.network.feature.common.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UpdateImageResponse(
    @SerialName("imageId")
    val id: Int,
    val url: String
)
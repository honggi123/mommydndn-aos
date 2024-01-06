package com.mommydndn.app.data.network.model.user.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UpdateProfileImageRequest(
    @SerialName("imageId")
    val id: Int
)

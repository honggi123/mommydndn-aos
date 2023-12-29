package com.mommydndn.app.data.network.model.request

import kotlinx.serialization.Serializable

@Serializable
data class UpdateProfileImageRequest(
    val imageId: Int
)

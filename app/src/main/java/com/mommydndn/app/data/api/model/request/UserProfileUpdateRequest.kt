package com.mommydndn.app.data.api.model.request

import kotlinx.serialization.Serializable

@Serializable
data class UserProfileUpdateRequest(
    val imageId: Int
)

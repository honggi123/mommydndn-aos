package com.mommydndn.app.data.api.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class ReissueResponse(
    val accessToken: String?,
    val refreshToken: String?
)
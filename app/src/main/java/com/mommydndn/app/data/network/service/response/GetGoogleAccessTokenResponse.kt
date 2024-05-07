package com.mommydndn.app.data.network.service.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetGoogleAccessTokenResponse(
    @SerialName("access_token")
    val accessToken: String?
)

package com.mommydndn.app.data.network.service.google.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetGoogleAccessTokenResponse(
    @SerialName("access_token")
    var accessToken: String,
)

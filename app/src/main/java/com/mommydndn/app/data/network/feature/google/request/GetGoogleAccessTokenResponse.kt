package com.mommydndn.app.data.network.feature.google.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetGoogleAccessTokenResponse(
    @SerialName("access_token")
    var accessToken: String,
)

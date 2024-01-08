package com.mommydndn.app.data.network.model.auth.google.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetGoogleAccessTokenResponse(
    @SerialName("access_token")
    var accessToken: String,
)

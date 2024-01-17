package com.mommydndn.app.data.network.service.response

import kotlinx.serialization.Serializable

@Serializable
data class RefreshAccessTokenResponse(
    val accessToken: String,
    val refreshToken: String
)
package com.mommydndn.app.data.api.model

import kotlinx.serialization.Serializable

@Serializable
data class LoginRequest(
    val accessToken: String,
    val oauthProvider: String
)

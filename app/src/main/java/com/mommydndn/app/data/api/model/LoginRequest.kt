package com.mommydndn.app.data.api.model

data class LoginRequest(
    val accessToken: String,
    val oauthProvider: String
)

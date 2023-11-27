package com.mommydndn.app.domain.model.user

data class OAuthToken(
    val accessToken: String,
    val refreshToken: String,
)

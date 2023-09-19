package com.mommydndn.app.data.model

data class SignUpRequest(
    val accessToken: String,
    val emdId: Int,
    val oauthProvider: String,
    val userType: String
)
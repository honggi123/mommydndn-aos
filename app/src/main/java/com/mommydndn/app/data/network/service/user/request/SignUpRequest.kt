package com.mommydndn.app.data.network.service.user.request

import kotlinx.serialization.Serializable

@Serializable
data class SignUpRequest(
    val accessToken: String,
    val emdId: Int,
    val oauthProvider: String,
    val userType: String
)

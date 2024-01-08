package com.mommydndn.app.data.network.feature.user.request

import kotlinx.serialization.Serializable

@Serializable
data class SignInRequest(
    val oAuthProvider: String,
    val accessToken: String
)

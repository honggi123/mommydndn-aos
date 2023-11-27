package com.mommydndn.app.data.api.model.request

import kotlinx.serialization.Serializable

@Serializable
data class SignInRequest(
    val accessToken: String,
    val oAuthProvider: String
)

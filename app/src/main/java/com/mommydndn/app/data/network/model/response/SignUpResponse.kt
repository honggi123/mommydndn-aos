package com.mommydndn.app.data.network.model.response

import kotlinx.serialization.Serializable

@Serializable
data class SignUpResponse(
    val accessToken: String,
    val refreshToken: String
)
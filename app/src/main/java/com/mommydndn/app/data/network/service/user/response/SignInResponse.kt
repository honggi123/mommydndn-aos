package com.mommydndn.app.data.network.service.user.response

import kotlinx.serialization.Serializable

@Serializable
data class SignInResponse(
    val accessToken: String?,
    val refreshToken: String?
)
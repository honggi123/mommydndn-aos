package com.mommydndn.app.data.network.service.request

import com.mommydndn.app.data.network.model.NetworkOAuthProvider
import kotlinx.serialization.Serializable

@Serializable
data class SignInRequest(
    val accessToken: String,
    val oauthProvider: NetworkOAuthProvider,
    val deviceToken: String?
)

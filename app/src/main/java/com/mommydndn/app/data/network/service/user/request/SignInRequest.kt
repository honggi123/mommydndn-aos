package com.mommydndn.app.data.network.service.user.request

import com.mommydndn.app.data.network.service.user.model.OAuthProviderApiModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SignInRequest(
    @SerialName("oauthProvider")
    val oAuthProvider: OAuthProviderApiModel,
    val accessToken: String,
    val deviceToken: String
)

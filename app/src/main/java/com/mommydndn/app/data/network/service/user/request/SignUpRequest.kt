package com.mommydndn.app.data.network.service.user.request

import com.mommydndn.app.data.network.service.user.model.OAuthProviderApiModel
import com.mommydndn.app.data.network.service.user.model.UserTypeApiModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SignUpRequest(
    @SerialName("emdId")
    val locationId: Int,
    @SerialName("userType")
    val userType: UserTypeApiModel,
    @SerialName("oauthProvider")
    val oAuthProvider: OAuthProviderApiModel,
    val accessToken: String,
)

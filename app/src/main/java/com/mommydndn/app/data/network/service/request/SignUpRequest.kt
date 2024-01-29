package com.mommydndn.app.data.network.service.request

import com.mommydndn.app.data.network.model.NetworkOAuthProvider
import com.mommydndn.app.data.network.model.NetworkUserType
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SignUpRequest(
    val accessToken: String,
    val oauthProvider: NetworkOAuthProvider,
    @SerialName("userType")
    val userType: NetworkUserType,
    @SerialName("emdId")
    val neighborhoodId: Int,
    val deviceToken: String?,
)

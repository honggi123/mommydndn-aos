package com.mommydndn.app.data.network.model.auth.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetGoogleAccessTokenRequest(
    @SerialName("grant_type")
    private val grantType: String,
    @SerialName("client_id")
    private val clientId: String,
    @SerialName("client_secret")
    private val clientSecret: String,
    private val code: String
)

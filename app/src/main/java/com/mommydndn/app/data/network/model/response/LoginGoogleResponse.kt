package com.mommydndn.app.data.network.model.response

import kotlinx.serialization.Serializable

@Serializable
data class LoginGoogleResponse(
    var access_token: String,
)

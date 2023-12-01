package com.mommydndn.app.data.network.model.request

import kotlinx.serialization.Serializable

@Serializable
data class GoogleLoginRequest(
    private val grant_type: String,
    private val client_id: String,
    private val client_secret: String,
    private val code: String
)

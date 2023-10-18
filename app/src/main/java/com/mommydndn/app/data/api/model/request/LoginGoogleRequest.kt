package com.mommydndn.app.data.api.model.request

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class LoginGoogleRequest(
    private val grant_type: String,
    private val client_id: String,
    private val client_secret: String,
    private val code: String
)

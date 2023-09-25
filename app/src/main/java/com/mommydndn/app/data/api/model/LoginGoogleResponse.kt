package com.mommydndn.app.data.api.model

import com.google.gson.annotations.SerializedName

data class LoginGoogleResponse(
    @SerializedName("access_token") var access_token: String,
)

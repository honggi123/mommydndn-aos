package com.mommydndn.app.data.model

import com.google.gson.annotations.SerializedName

data class ReissueResponse(
    @SerializedName("accessToken")
    val accessToken: String?,
    @SerializedName("refreshToken")
    val refreshToken: String?
)
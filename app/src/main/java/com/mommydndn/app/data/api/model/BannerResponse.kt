package com.mommydndn.app.data.api.model


import com.google.gson.annotations.SerializedName

data class BannerResponse(
    @SerializedName("bannerId")
    val bannerId: Int,
    @SerializedName("targetUrl")
    val targetUrl: String?,
    @SerializedName("url")
    val url: String?
)
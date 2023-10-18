package com.mommydndn.app.data.api.model.response


import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class BannerResponse(
    val bannerId: Int,
    val targetUrl: String?,
    val url: String?
)
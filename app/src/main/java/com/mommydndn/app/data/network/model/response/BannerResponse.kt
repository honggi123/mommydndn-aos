package com.mommydndn.app.data.network.model.response


import kotlinx.serialization.Serializable

@Serializable
data class BannerResponse(
    val bannerId: Int,
    val targetUrl: String?,
    val url: String?
)
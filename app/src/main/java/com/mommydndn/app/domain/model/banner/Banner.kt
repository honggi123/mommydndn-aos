package com.mommydndn.app.domain.model.banner

import kotlinx.serialization.Serializable

@Serializable
data class Banner(
    val bannerId: Int,
    val url: String,
    val targetUrl: String
)

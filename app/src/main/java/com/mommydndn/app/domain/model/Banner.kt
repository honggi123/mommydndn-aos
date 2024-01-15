package com.mommydndn.app.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Banner(
    val id: Int,
    val imageUrl: String,
    val url: String,
)

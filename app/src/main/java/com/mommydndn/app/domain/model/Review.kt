package com.mommydndn.app.domain.model

import com.mommydndn.app.data.network.model.NetworkCareType
import java.util.Date

data class Review(
    val id: String,
    val reviewerNickname: String, // TODO
    val rating: Double,
    val careTypes: List<NetworkCareType>,
    val content: String,
    val createdAt: Date,
)
package com.mommydndn.app.data.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NetworkCareAgencyManager(
    @SerialName("userId")
    val id: Long,
    val nickname: String,
    @SerialName("profileUrl")
    val imageUrl: String,
    val isDnDnAuthenticated: Boolean,
    val dndnScore: Double,
    val createdAt: Long,
    val matchingCount: Int,
    val reviewCount: Int,
    val responseRate: String,
    @SerialName("reviewList")
    val reviews: List<NetworkCareReview>,
)
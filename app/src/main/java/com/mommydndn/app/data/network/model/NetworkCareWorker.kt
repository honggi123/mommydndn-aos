package com.mommydndn.app.data.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NetworkCareWorker(
    @SerialName("userId")
    val id: Long,
    val nickname: String,
    @SerialName("imageUrl")
    val profileImageUrl: String,
    val isDnDnAuthenticated: Boolean,
    val dndnScore: Double,
    @SerialName("certificationList")
    val certifications: List<NetworkCertification>,
    val createdAt: Long,
    val matchingCount: Int,
    val reviewCount: Int,
    val responseRate: String,
    @SerialName("reviewList")
    val reviews: List<NetworkCareReview>,
)
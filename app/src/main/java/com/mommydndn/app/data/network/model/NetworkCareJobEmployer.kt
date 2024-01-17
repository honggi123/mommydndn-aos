package com.mommydndn.app.data.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NetworkCareJobEmployer(
    @SerialName("userId")
    val id: Int,
    val nickname: String,
    @SerialName("profileUrl")
    val imageUrl: String?,
    @SerialName("neighborhood")
    val neighborhoodName: String,
    val isDnDnAuthenticated: Boolean,
    val dndnScore: Double,
    val certificationName: String, // TODO: Nullable?
    val createdAt: Long,
    val matchingCount: Int,
    val reviewCount: Int,
    val responseRate: String,
    val latestReview: NetworkCareReview?, // TODO: 작성한 리뷰? 받은 리뷰?, Nullable?
)
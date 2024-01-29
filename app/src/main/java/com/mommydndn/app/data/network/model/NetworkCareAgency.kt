package com.mommydndn.app.data.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NetworkCareAgency(
    @SerialName("companyId")
    val id: Long,
    @SerialName("authorId")
    val managerId: Long,
    @SerialName("nickname")
    val name: String,
    @SerialName("neighborhood")
    val neighborhoodName: String,
    @SerialName("profileUrl")
    val profileImageUrl: String,
    val isDndnAuthenticated: Boolean,
    val dndnScore: Double,
    @SerialName("caringTypeCodeList")
    val careTypes: List<NetworkCareType>,
    val matchingCount: Int,
    val reviewCount: Int,
    val responseRate: String,
    val isLiked: Boolean,
)

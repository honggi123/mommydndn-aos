package com.mommydndn.app.data.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NetworkCareReview(
    @SerialName("caringReviewId")
    val id: Int,
    @SerialName("nickname")
    val reviewerNickname: String,
    @SerialName("rate")
    val rating: Double,
    @SerialName("caringTypeCodeList")
    val careTypes: List<NetworkCareType>,
    val content: String,
    val createdAt: Long,
)
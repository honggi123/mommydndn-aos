package com.mommydndn.app.data.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NetworkNearestCareWorker(
    @SerialName("jobSeekerId") val id: Int,
    @SerialName("caringType") val careType: NetworkCareType,
    val nickname: String,
    @SerialName("profileUrl") val imageUrl: String,
    val ageAndGender: String
)
package com.mommydndn.app.data.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NetworkNearbyCareWorker(
    @SerialName("jobSeekerId")
    val id: Long,
    @SerialName("caringType")
    val careType: NetworkCareType,
    val nickname: String,
    @SerialName("profileUrl")
    val profileImageUrl: String,
    val ageAndGender: String,
)
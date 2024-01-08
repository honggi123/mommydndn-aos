package com.mommydndn.app.data.network.feature.location.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

typealias GetNearestCareProviderListResponse = List<NearestCareProviderApiModel>

@Serializable
data class NearestCareProviderApiModel(
    @SerialName("jobSeekerId")
    val id: Int,
    @SerialName("caringType")
    val careType: String,
    val nickname: String,
    val profileUrl: String,
    val ageAndGender: String
)
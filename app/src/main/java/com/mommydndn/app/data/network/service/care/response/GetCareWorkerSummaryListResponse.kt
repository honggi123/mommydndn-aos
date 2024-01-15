package com.mommydndn.app.data.network.service.care.response

import com.mommydndn.app.data.network.model.NetworkCareType
import com.mommydndn.app.data.network.model.NetworkMeta
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetCareWorkerSummaryListResponse(
    @SerialName("jobSeekerSummaryList")
    val items: List<CareWorkerSummaryApiModel>,
    val meta: NetworkMeta
)

@Serializable
data class CareWorkerSummaryApiModel(
    @SerialName("jobSeekerId")
    val id: Int,
    @SerialName("neighborhood")
    val neighborhoodName: String,
    @SerialName("caringTypeCodeList")
    val careTypes: List<NetworkCareType>,
    val nickname: String,
    val profileUrl: String,
    val isDndnAuthenticated: Boolean,
    val dndnScore: Double,
    val ageAndGender: String,
    val matchingCount: Int,
    val reviewCount: Int,
    val responseRate: String,
    val isLiked: Boolean
)

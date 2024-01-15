package com.mommydndn.app.data.network.service.care.response

import com.mommydndn.app.data.network.model.NetworkCareType
import com.mommydndn.app.data.network.model.NetworkMeta
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetAgencyCareWorkerSummaryListResponse(
    @SerialName("companySummaryList")
    val items: List<AgencyCareWorkerSummaryApiModel>,
    @SerialName("meta")
    val meta: NetworkMeta
)

@Serializable
data class AgencyCareWorkerSummaryApiModel(
    @SerialName("companyId")
    val id: Int,
    @SerialName("authorId")
    val writerId: Int,
    @SerialName("neighborhood")
    val neighborhoodName: String,
    @SerialName("caringTypeCodeList")
    val careTypes: List<NetworkCareType>,
    val matchingCount: Int,
    val reviewCount: Int,
    val responseRate: String,
    val isLiked: Boolean,
    val dndnScore: Double,
    val nickname: String,
    val profileUrl: String,
    val isDndnAuthenticated: Boolean
)


package com.mommydndn.app.data.network.service.care.response

import com.mommydndn.app.data.network.service.care.model.CareTypeApiModel
import com.mommydndn.app.data.network.service.common.model.MetaApiModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetAgencyCareProviderSummaryListResponse(
    @SerialName("companySummaryList")
    val items: List<AgencyCareProviderSummaryApiModel>,
    @SerialName("meta")
    val meta: MetaApiModel
)

@Serializable
data class AgencyCareProviderSummaryApiModel(
    @SerialName("companyId")
    val id: Int,
    @SerialName("authorId")
    val writerId: Int,
    @SerialName("neighborhood")
    val neighborhoodName: String,
    @SerialName("caringTypeCodeList")
    val careTypes: List<CareTypeApiModel>,
    val matchingCount: Int,
    val reviewCount: Int,
    val responseRate: String,
    val isLiked: Boolean,
    val dndnScore: Double,
    val nickname: String,
    val profileUrl: String,
    val isDndnAuthenticated: Boolean
)


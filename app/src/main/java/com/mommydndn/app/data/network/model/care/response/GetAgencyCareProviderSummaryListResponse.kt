package com.mommydndn.app.data.network.model.care.response

import com.mommydndn.app.data.model.care.CaringType
import com.mommydndn.app.data.model.care.CaringTypeSerializer
import com.mommydndn.app.data.network.model.common.MetaApiModel
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
    @SerialName("neighborhood")
    val neighborhoodName: String,
    @SerialName("caringTypeCodeList")
    val careTypeList: List<@Serializable(with = CaringTypeSerializer::class) CaringType>,
    val matchingCount: Int,
    val reviewCount: Int,
    val responseRate: String,
    val isLiked: Boolean,
    val dndnScore: Double,
    val authorId: Int,
    val nickname: String,
    val profileUrl: String,
    val isDndnAuthenticated: Boolean
)
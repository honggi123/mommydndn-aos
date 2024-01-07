package com.mommydndn.app.data.network.model.care.response

import com.mommydndn.app.data.model.care.CaringType
import com.mommydndn.app.data.model.care.CaringTypeSerializer
import com.mommydndn.app.data.network.model.care.CareTypeApiModel
import com.mommydndn.app.data.network.model.common.MetaApiModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetCareProviderSummaryListResponse(
    @SerialName("jobSeekerSummaryList")
    val items: List<CareProviderSummaryApiModel>,
    val meta: MetaApiModel
)

@Serializable
data class CareProviderSummaryApiModel(
    @SerialName("jobSeekerId")
    val id: Int,
    @SerialName("neighborhood")
    val neighborhoodName: String,
    @SerialName("caringTypeCodeList")
    val careTypes: List<CareTypeApiModel>,
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

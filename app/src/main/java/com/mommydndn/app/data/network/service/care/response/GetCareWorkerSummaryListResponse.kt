package com.mommydndn.app.data.network.service.care.response

import com.mommydndn.app.data.network.service.care.model.CareTypeApiModel
import com.mommydndn.app.data.network.service.common.model.MetaApiModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetCareWorkerSummaryListResponse(
    @SerialName("jobSeekerSummaryList")
    val items: List<CareWorkerSummaryApiModel>,
    val meta: MetaApiModel
)

@Serializable
data class CareWorkerSummaryApiModel(
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

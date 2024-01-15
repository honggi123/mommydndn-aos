package com.mommydndn.app.data.network.service.care.response

import com.mommydndn.app.data.network.model.NetworkCareType
import com.mommydndn.app.data.network.model.NetworkNeighborhood
import com.mommydndn.app.data.network.service.care.model.AgencyOtherConditionApiModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetAgencyCareWorkerResponse(
    @SerialName("companyId")
    val id: Int,
    @SerialName("companyAuthor")
    val writer: AgencyCareWorkerWriterApiModel,
    @SerialName("coverImageList")
    val coverImages: List<AgencyCareWorkerCoverImageApiModel>,
    @SerialName("emd")
    val neighborhood: NetworkNeighborhood,
    @SerialName("introLine")
    val introduction: String,
    @SerialName("maxMonthlySalary")
    val maxSalary: Int,
    @SerialName("minMonthlySalary")
    val minSalary: Int,
    @SerialName("caringTypeCodeList")
    val careTypes: List<NetworkCareType>,
    @SerialName("comOtherConditionList")
    val otherConditions: List<AgencyOtherConditionApiModel>,
    val isLiked: Boolean,
    val commission: Int,
)

@Serializable
data class AgencyCareWorkerCoverImageApiModel(
    @SerialName("imageId")
    val id: Int,
    val url: String
)

@Serializable
data class AgencyCareWorkerWriterApiModel(
    @SerialName("userId")
    val id: Int,
    @SerialName("reviewList")
    val reviews: List<AgencyCareWorkerReviewApiModel>,
    val dndnScore: Int,
    val isDnDnAuthenticated: Boolean,
    val matchingCount: Int,
    val nickname: String,
    val profileUrl: String,
    val responseRate: String,
    val reviewCount: Int,
    val createdAt: Int
)

@Serializable
data class AgencyCareWorkerReviewApiModel(
    @SerialName("caringReviewId")
    val id: Int,
    @SerialName("caringTypeCodeList")
    val careTypes: List<NetworkCareType>,
    @SerialName("rate")
    val rate: Int,
    val content: String,
    val nickname: String,
    val createdAt: String
)
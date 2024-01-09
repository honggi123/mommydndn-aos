package com.mommydndn.app.data.network.service.care.response


import com.mommydndn.app.data.network.service.care.model.AgencyOtherConditionApiModel
import com.mommydndn.app.data.network.service.care.model.CareTypeApiModel
import com.mommydndn.app.data.network.service.common.model.LocationApiModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetAgencyCareProviderResponse(
    @SerialName("companyId")
    val id: Int,
    @SerialName("companyAuthor")
    val writer: AgencyCareProviderWriterApiModel,
    @SerialName("coverImageList")
    val coverImages: List<AgencyCareProviderCoverImageApiModel>,
    @SerialName("emd")
    val neighborhood: LocationApiModel,
    @SerialName("introLine")
    val introduction: String,
    @SerialName("maxMonthlySalary")
    val maxSalary: Int,
    @SerialName("minMonthlySalary")
    val minSalary: Int,
    @SerialName("caringTypeCodeList")
    val careTypes: List<CareTypeApiModel>,
    @SerialName("comOtherConditionList")
    val otherConditions: List<AgencyOtherConditionApiModel>,
    val isLiked: Boolean,
    val commission: Int,
)

@Serializable
data class AgencyCareProviderCoverImageApiModel(
    @SerialName("imageId")
    val id: Int,
    val url: String
)

@Serializable
data class AgencyCareProviderWriterApiModel(
    @SerialName("userId")
    val id: Int,
    @SerialName("reviewList")
    val reviews: List<AgencyCareProviderReviewApiModel>,
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
data class AgencyCareProviderReviewApiModel(
    @SerialName("caringReviewId")
    val id: Int,
    @SerialName("caringTypeCodeList")
    val careTypes: List<CareTypeApiModel>,
    @SerialName("rate")
    val rate: Int,
    val content: String,
    val nickname: String,
    val createdAt: String
)
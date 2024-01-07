package com.mommydndn.app.data.network.model.care.response


import com.mommydndn.app.data.network.model.common.LocationApiModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetCareProviderResponse(
    @SerialName("jobSeekerId")
    val id: Int,
    @SerialName("emd")
    val neighborhood: LocationApiModel,
    @SerialName("introLine")
    val introduction: String,
    @SerialName("jobSeekerAuthor")
    val writer: JobSeekerWriter,
    @SerialName("salaryTypeCode")
    val salaryType: String,
    @SerialName("caringTypeCodeList")
    val careTypes: List<String>,
    @SerialName("indOtherConditionList")
    val indOtherConditionList: List<String>,
    val latitude: Int,
    val longitude: Int,
    val salary: Int,
    val isLiked: Boolean
)

@Serializable
data class JobSeekerWriter(
    @SerialName("userId")
    val userId: Int,
    @SerialName("reviewList")
    val reviews: List<ReviewApiModel>,
    @SerialName("certificationList")
    val certifications: List<CertificationApiModel>,
    val matchingCount: Int,
    val nickname: String,
    val profileUrl: String,
    val responseRate: String,
    val reviewCount: Int,
    val createdAt: Int,
    val dndnScore: Int,
    val isDnDnAuthenticated: Boolean,
)

@Serializable
data class ReviewApiModel(
    @SerialName("caringReviewId")
    val id: Int,
    @SerialName("caringTypeCodeList")
    val careTypes: List<String>,
    val content: String,
    val nickname: String,
    val rate: Int,
    val createdAt: String
)

@Serializable
data class CertificationApiModel(
    @SerialName("certificationName")
    val certificationName: String,
    @SerialName("certificationTypeCode")
    val certificationTypeCode: String,
    @SerialName("userCertificationId")
    val id: Int,
    val updatedAt: Int
)
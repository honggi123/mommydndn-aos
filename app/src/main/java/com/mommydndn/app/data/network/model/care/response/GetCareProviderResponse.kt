package com.mommydndn.app.data.network.model.care.response


import com.mommydndn.app.data.network.model.care.CareTypeApiModel
import com.mommydndn.app.data.network.model.care.IndividualOtherConditionApiModel
import com.mommydndn.app.data.network.model.care.SalaryTypeApiModel
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
    val salaryType: SalaryTypeApiModel,
    @SerialName("caringTypeCodeList")
    val careTypes: List<CareTypeApiModel>,
    @SerialName("indOtherConditionList")
    val otherConditionList: List<IndividualOtherConditionApiModel>,
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
    @SerialName("userCertificationId")
    val id: Int,
    @SerialName("certificationName")
    val certificationName: String,
    @SerialName("certificationTypeCode")
    val certificationType: String,
    val updatedAt: Int
)
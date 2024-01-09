package com.mommydndn.app.data.network.service.user.response

import com.mommydndn.app.data.network.service.care.model.CareTypeApiModel
import com.mommydndn.app.data.network.service.care.model.CertificationTypeApiModel
import com.mommydndn.app.data.network.service.common.model.LocationApiModel
import com.mommydndn.app.data.network.service.user.model.GenderTypeApiModel
import com.mommydndn.app.data.network.service.user.model.UserStatusApiModel
import com.mommydndn.app.data.network.service.user.model.UserTypeApiModel
import com.mommydndn.app.domain.model.location.Neighborhood
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetUserResponse(
    @SerialName("userId")
    val userId: Int,
    @SerialName("caringReviewList")
    val reviews: List<CaringReview>,
    @SerialName("certificationList")
    val certifications: List<Certification>,
    @SerialName("emd")
    val neightborhood: LocationApiModel,
    @SerialName("hasJobSeeker")
    val isCareProviderCreated: Boolean,
    @SerialName("jobSeekerId")
    val careProviderId: Int?,
    @SerialName("hasCompany")
    val isAgencyCareProviderCreated: Boolean,
    @SerialName("companyId")
    val agencyCareProviderId: Int?,
    @SerialName("userStatus")
    val userStatus: UserStatusApiModel,
    @SerialName("userType")
    val userType: UserTypeApiModel,
    @SerialName("gender")
    val gender: GenderTypeApiModel?,
    val age: Int?,
    val birthDate: String?,
    val profileUrl: String?,
    val dndnScore: Double,
    val responseRate: String,
    val reviewCount: Int,
    val isDnDnAuthenticated: Boolean,
    val matchingCount: Int,
    val nickname: String,
    val createdAt: Long
)

@Serializable
data class CaringReview(
    @SerialName("caringReviewId")
    val id: Int,
    @SerialName("caringTypeCodeList")
    val careTypes: List<CareTypeApiModel>,
    val content: String,
    val nickname: String,
    val rate: Double,
    val createdAt: Long
)

@Serializable
data class Certification(
    @SerialName("userCertificationId")
    val id: Int,
    @SerialName("certificationName")
    val name: String,
    @SerialName("certificationTypeCode")
    val type: CertificationTypeApiModel,
    val updatedAt: Long,
)




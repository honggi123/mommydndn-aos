package com.mommydndn.app.data.network.service.user.response

import com.mommydndn.app.data.network.model.NetworkCareType
import com.mommydndn.app.data.network.model.NetworkNeighborhood
import com.mommydndn.app.data.network.service.user.model.UserTypeApiModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetUserResponse(
    @SerialName("userId")
    val userId: Int,
    @SerialName("caringReviewList")
    val reviews: List<CaringReview>,
//    @SerialName("certificationList")
//    val certifications: List<NetworkCertification>,
    @SerialName("emd")
    val location: NetworkNeighborhood,
    @SerialName("hasJobSeeker")
    val isCareWorkerCreated: Boolean,
    @SerialName("jobSeekerId")
    val CareWorkerId: Int?,
    @SerialName("hasCompany")
    val isAgencyCareWorkerCreated: Boolean,
    @SerialName("companyId")
    val agencyCareWorkerId: Int?,
//    @SerialName("userStatus")
//    val userStatus: NetworkUserStatus,
    @SerialName("userType")
    val userType: UserTypeApiModel,
//    @SerialName("gender")
//    val gender: NetworkGender?,
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
    val careTypes: List<NetworkCareType>,
    val content: String,
    val nickname: String,
    val rate: Double,
    val createdAt: Long
)
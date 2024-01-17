package com.mommydndn.app.data.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NetworkUser(
    @SerialName("userId") val id: Long,
    val email: String,
    @SerialName("realName") val name: String,
    val userType: NetworkUserType,
    val userStatus: NetworkUserStatus,
    val nickname: String,
    val gender: NetworkUserGender?,
    @SerialName("profileUrl") val profileImageUrl: String?,
    val age: Int?,
    val birthDate: String?,
    @SerialName("emd") val neighborhood: NetworkNeighborhood,
    @SerialName("hasJobSeeker") val registerWorkerProfile: Boolean,
    @SerialName("jobSeekerId") val workerProfileId: Long?,
    @SerialName("hasCompany") val registerAgencyProfile: Boolean,
    @SerialName("companyId") val agencyProfileId: Long?,
    val isDnDnAuthenticated: Boolean,
    val dndnScore: Double,
    val matchingCount: Int,
    val reviewCount: Int,
    val responseRate: String,
    @SerialName("caringReviewList") val reviews: List<NetworkCareReview>,
    @SerialName("certificationList") val certifications: List<NetworkCertification>,
    val createdAt: Long,
)
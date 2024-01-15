package com.mommydndn.app.data.network.model

import kotlinx.serialization.SerialName

data class NetworkUser(
    @SerialName("userId") val id: Int,
    val email: String,
    @SerialName("realName") val name: String,
    val userType: NetworkUserType,
    val userStatus: NetworkUserStatus,
    val nickname: String,
    val gender: NetworkGender?,
    @SerialName("profileUrl") val imageUrl: String?,
    val age: Int?,
    val birthDate: String?,
    @SerialName("emd") val neighborhood: NetworkNeighborhood,
    @SerialName("hasJobSeeker") val registerWorkerProfile: Boolean,
    @SerialName("jobSeekerId") val workerProfileId: Int?,
    @SerialName("hasCompany") val registerAgencyProfile: Boolean,
    @SerialName("companyId") val agencyProfileId: Int?,
    val isDnDnAuthenticated: Boolean,
    val dndnScore: Double,
    val matchingCount: Int,
    val reviewCount: Int,
    val responseRate: String,
    @SerialName("caringReviewList") val reviews: List<NetworkCareReview>,
    @SerialName("certificationList") val certifications: List<NetworkCertification>,
    val createdAt: Long,
)

enum class NetworkUserType {
    Individual, Agency
}

enum class NetworkUserStatus {
    Active, Withdrawn, Banned
}

enum class NetworkGender {
    Male, Female
}
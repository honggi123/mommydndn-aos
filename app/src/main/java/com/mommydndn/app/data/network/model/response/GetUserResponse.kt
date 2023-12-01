package com.mommydndn.app.data.network.model.response

import com.mommydndn.app.data.model.care.CaringType
import com.mommydndn.app.data.model.care.CaringTypeSerializer
import com.mommydndn.app.data.model.care.CertificationType
import com.mommydndn.app.data.model.care.CertificationTypeSerializer
import com.mommydndn.app.data.model.map.EmdItem
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetUserResponse(
    @SerialName("age")
    val age: Int?,
    @SerialName("caringReviewList")
    val caringReviewList: List<CaringReview>,
    @SerialName("certificationList")
    val certificationList: List<Certification>,
    @SerialName("companyId")
    val companyId: Int?,
    @SerialName("createdAt")
    val createdAt: Long,
    @SerialName("dndnScore")
    val dndnScore: Double,
    @SerialName("emd")
    val emd: EmdItem,
    @SerialName("gender")
    val gender: String?,
    @SerialName("hasCompany")
    val hasCompany: Boolean,
    @SerialName("hasJobSeeker")
    val hasJobSeeker: Boolean,
    @SerialName("isDnDnAuthenticated")
    val isDnDnAuthenticated: Boolean,
    @SerialName("jobSeekerId")
    val jobSeekerId: Int?,
    @SerialName("matchingCount")
    val matchingCount: Int,
    @SerialName("nickname")
    val nickname: String,
    @SerialName("profileUrl")
    val profileUrl: String?,
    @SerialName("responseRate")
    val responseRate: String,
    @SerialName("reviewCount")
    val reviewCount: Int,
    @SerialName("userId")
    val userId: Int,
    @SerialName("userStatus")
    val userStatus: String,
    @SerialName("userType")
    val userType: String
)

@Serializable
data class CaringReview(
    @SerialName("caringReviewId")
    val caringReviewId: Int,
    @SerialName("caringTypeCodeList")
    val caringTypeCodeList: List<@Serializable(with = CaringTypeSerializer::class) CaringType>,
    @SerialName("content")
    val content: String,
    @SerialName("createdAt")
    val createdAt: Long,
    @SerialName("nickname")
    val nickname: String,
    @SerialName("rate")
    val rate: Double
)

@Serializable
data class Certification(
    @SerialName("certificationName")
    val certificationName: String,
    @Serializable(with = CertificationTypeSerializer::class)
    @SerialName("certificationTypeCode")
    val certificationTypeCode: CertificationType,
    @SerialName("updatedAt")
    val updatedAt: Long,
    @SerialName("userCertificationId")
    val userCertificationId: Int
)

@Serializable
data class Emd(
    @SerialName("ctprvnName")
    val ctprvnName: String,
    @SerialName("fullName")
    val fullName: String,
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    val name: String,
    @SerialName("sigName")
    val sigName: String
)
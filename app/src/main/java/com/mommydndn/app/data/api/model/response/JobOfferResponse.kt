package com.mommydndn.app.data.api.model.response


import com.mommydndn.app.data.model.care.CaringType
import com.mommydndn.app.data.model.care.CaringTypeSerializer
import com.mommydndn.app.data.model.care.SalaryType
import com.mommydndn.app.data.model.care.SalaryTypeSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class JobOfferResponse(
    @SerialName("applicantCount")
    val applicantCount: Int,
    @Serializable(with = CaringTypeSerializer::class)
    @SerialName("caringTypeCodeList")
    val caringTypeCodeList: List<CaringType>,
    @SerialName("content")
    val content: String,
    @SerialName("createdAt")
    val createdAt: Int,
    @SerialName("days")
    val days: List<String>?,
    @SerialName("emdId")
    val emdId: Int,
    @SerialName("endDate")
    val endDate: Int?,
    @SerialName("endTime")
    val endTime: String?,
    @SerialName("hits")
    val hits: Int,
    @SerialName("imageList")
    val imageList: List<ImageResponse>,
    @SerialName("indOtherConditionCodeList")
    val indOtherConditionCodeList: List<String>,
    @SerialName("isClosed")
    val isClosed: Boolean,
    @SerialName("isLiked")
    val isLiked: Boolean,
    @SerialName("jobOfferAuthor")
    val jobOfferAuthor: JobOfferAuthor,
    @SerialName("jobOfferId")
    val jobOfferId: Int,
    @SerialName("latitude")
    val latitude: Double,
    @SerialName("likeCount")
    val likeCount: Int,
    @SerialName("longitude")
    val longitude: Double,
    @SerialName("salary")
    val salary: Int?,
    @Serializable(with = SalaryTypeSerializer::class)
    @SerialName("salaryTypeCode")
    val salaryTypeCode: SalaryType,
    @SerialName("startDate")
    val startDate: Int?,
    @SerialName("startTime")
    val startTime: String?,
    @SerialName("title")
    val title: String
)

@Serializable
data class JobOfferAuthor(
    @SerialName("certificationName")
    val certificationName: String,
    @SerialName("createdAt")
    val createdAt: Int,
    @SerialName("dndnScore")
    val dndnScore: Double,
    @SerialName("isDnDnAuthenticated")
    val isDnDnAuthenticated: Boolean,
    @SerialName("latestReview")
    val latestReview: LatestReview,
    @SerialName("matchingCount")
    val matchingCount: Int,
    @SerialName("neighborhood")
    val neighborhood: String,
    @SerialName("nickname")
    val nickname: String,
    @SerialName("profileUrl")
    val profileUrl: String,
    @SerialName("responseRate")
    val responseRate: String,
    @SerialName("reviewCount")
    val reviewCount: Int,
    @SerialName("userId")
    val userId: Int
)

@Serializable
data class LatestReview(
    @SerialName("caringReviewId")
    val caringReviewId: Int,
    @SerialName("caringTypeCodeList")
    val caringTypeCodeList: List<String>,
    @SerialName("content")
    val content: String,
    @SerialName("createdAt")
    val createdAt: Int,
    @SerialName("nickname")
    val nickname: String,
    @SerialName("rate")
    val rate: Int
)
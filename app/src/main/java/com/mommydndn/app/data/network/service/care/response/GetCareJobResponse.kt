package com.mommydndn.app.data.network.service.care.response

import com.mommydndn.app.data.network.model.NetworkCareType
import com.mommydndn.app.data.network.model.NetworkNeighborhood
import com.mommydndn.app.data.network.service.care.model.NetworkWorkerOtherCondition
import com.mommydndn.app.data.network.service.care.model.SalaryTypeApiModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetCareJobResponse(
    @SerialName("jobOfferId")
    val id: Int,
    @SerialName("emd")
    val workingNeighborhood: NetworkNeighborhood,
    @SerialName("days")
    val daysOfWeek: List<String>?,
    @SerialName("dateList")
    val oneTimeWorkDateList: List<String>,
    @SerialName("startDate")
    val regularWorkStartDate: Long?,
    @SerialName("endDate")
    val regularWorkEndDate: Long?,
    @SerialName("startTime")
    val regularWorkStartTime: String?,
    @SerialName("endTime")
    val regularWorkEndTime: String?,
    @SerialName("jobOfferAuthor")
    val writer: CareJobWriterApiModel,
    @SerialName("hits")
    val views: Int,
    @SerialName("salary")
    val pay: Int?,
    @SerialName("imageList")
    val images: List<CareJobImageApiModel>,
    @SerialName("salaryTypeCode")
    val salaryType: SalaryTypeApiModel,
    @SerialName("caringTypeCodeList")
    val careTypes: List<NetworkCareType>,
    @SerialName("indOtherConditionCodeList")
    val indOtherConditionList: List<NetworkWorkerOtherCondition>,
    val title: String,
    val content: String,
    val createdAt: Long,
    val applicantCount: Int,
    val isClosed: Boolean,
    val isLiked: Boolean,
    val latitude: Double,
    val likeCount: Int,
    val longitude: Double
)

@Serializable
data class CareJobWriterApiModel(
    @SerialName("neighborhood")
    val neighborhoodName: String,
    val userId: Int,
    val certificationName: String?,
    val createdAt: Long,
    val dndnScore: Double,
    val isDnDnAuthenticated: Boolean,
    val latestReview: CareJobReviewApiModel?,
    val matchingCount: Int,
    val nickname: String,
    val profileUrl: String?,
    val responseRate: String,
    val reviewCount: Int,
)

@Serializable
data class CareJobReviewApiModel(
    @SerialName("caringReviewId")
    val id: Int,
    @SerialName("caringTypeCodeList")
    val careTypeList: List<String>,
    val content: String,
    val nickname: String,
    val rate: Double,
    val createdAt: Long
)

@Serializable
data class CareJobImageApiModel(
    @SerialName("imageId")
    val id: Int,
    val url: String
)
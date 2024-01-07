package com.mommydndn.app.data.network.model.care.response


import com.mommydndn.app.data.model.care.CaringType
import com.mommydndn.app.data.model.care.CaringTypeSerializer
import com.mommydndn.app.data.model.care.EtcConditionType
import com.mommydndn.app.data.model.care.EtcConditionTypeSerializer
import com.mommydndn.app.data.model.care.SalaryType
import com.mommydndn.app.data.model.care.SalaryTypeSerializer
import com.mommydndn.app.data.network.model.common.LocationApiModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

// TODO
@Serializable
data class GetJobOpeningResponse(
    @SerialName("jobOfferId")
    val id: Int,
    @SerialName("emd")
    val workingNeighborhood: LocationApiModel,
    @SerialName("days")
    val daysOfWeek: List<String>?,
    @SerialName("startDate")
    val startDate: Long?,
    @SerialName("endDate")
    val endDate: Long?,
    @SerialName("endTime")
    val endTime: String?,
    @SerialName("startTime")
    val startTime: String?,
    @SerialName("jobOfferAuthor")
    val jobOpeningAuthor: JobOpeningAuthorApiModel,
    @SerialName("hits")
    val views: Int,
    @SerialName("salary")
    val pay: Int?,
    @Serializable(with = SalaryTypeSerializer::class)
    @SerialName("salaryTypeCode")
    val salaryType: SalaryType,
    @SerialName("caringTypeCodeList")
    val careTypes: List<@Serializable(with = CaringTypeSerializer::class) CaringType>,
    @SerialName("indOtherConditionCodeList")
    val indOtherConditionCodeList: List<@Serializable(with = EtcConditionTypeSerializer::class) EtcConditionType>,
    val title: String,
    val content: String,
    val createdAt: Long,
    val applicantCount: Int,
    val isClosed: Boolean,
    val isLiked: Boolean,
    val latitude: Double,
    val likeCount: Int,
    val longitude: Double,
    val imageList: List<ImageApiModel>
)

@Serializable
data class JobOpeningAuthorApiModel(
    val certificationName: String?,
    val createdAt: Long,
    val dndnScore: Double,
    val isDnDnAuthenticated: Boolean,
    val latestReview: LatestReviewApiModel?,
    val matchingCount: Int,
    val neighborhood: String,
    val nickname: String,
    val profileUrl: String?,
    val responseRate: String,
    val reviewCount: Int,
    val userId: Int
)

@Serializable
data class LatestReviewApiModel(
    @SerialName("caringReviewId")
    val id: Int,
    @SerialName("caringTypeCodeList")
    val careTypeList: List<String>,
    val content: String,
    val createdAt: Long,
    val nickname: String,
    val rate: Double
)

@Serializable
data class ImageApiModel(
    @SerialName("imageId")
    val id: Int,
    val url: String
)
package com.mommydndn.app.data.model.care.summary

import com.mommydndn.app.data.network.model.response.Meta
import com.mommydndn.app.data.model.care.CaringType
import com.mommydndn.app.data.model.care.CaringTypeSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class JobSeekerSummary(
    @SerialName("jobSeekerSummaryList")
    val jobSeekerSummaryList: List<JobSeekerSummaryItem>,
    @SerialName("meta")
    val meta: Meta
)

@Serializable
data class JobSeekerSummaryItem(
    @SerialName("jobSeekerId")
    val jobSeekerId: Int,
    @SerialName("nickname")
    val nickname: String,
    @SerialName("neighborhood")
    val neighborhood: String,
    @SerialName("profileUrl")
    val profileUrl: String,
    @SerialName("isDndnAuthenticated")
    val isDndnAuthenticated: Boolean,
    @SerialName("dndnScore")
    val dndnScore: Double,
    @SerialName("caringTypeCodeList")
    val caringTypeCodeList: List<@Serializable(with = CaringTypeSerializer::class) CaringType>,
    @SerialName("ageAndGender")
    val ageAndGender: String,
    @SerialName("matchingCount")
    val matchingCount: Int,
    @SerialName("reviewCount")
    val reviewCount: Int,
    @SerialName("responseRate")
    val responseRate: String,
    @SerialName("isLiked")
    val isLiked: Boolean
)

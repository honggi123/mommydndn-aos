package com.mommydndn.app.data.model.care.summary

import com.mommydndn.app.data.api.model.response.Meta
import com.mommydndn.app.data.model.care.CaringType
import com.mommydndn.app.data.model.care.CaringTypeSerializer
import com.mommydndn.app.data.model.care.SalaryType
import com.mommydndn.app.data.model.care.SalaryTypeSerializer
import com.mommydndn.app.data.model.common.DayOfWeekType
import com.mommydndn.app.data.model.common.DayOfWeekTypeSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CompanySummary(
    @SerialName("companySummaryList")
    val jobOfferSummaryList: List<CompanySummaryListItem>,
    @SerialName("meta")
    val meta: Meta
)

@Serializable
data class CompanySummaryListItem(
    @SerialName("companyId")
    val companyId: Int,
    @SerialName("authorId")
    val authorId: Int,
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
    @SerialName("matchingCount")
    val matchingCount: Int,
    @SerialName("reviewCount")
    val reviewCount: Int,
    @SerialName("responseRate")
    val responseRate: String,
    @SerialName("isLiked")
    val isLiked: Boolean
) : SummaryItem()
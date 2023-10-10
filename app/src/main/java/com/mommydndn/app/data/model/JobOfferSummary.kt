package com.mommydndn.app.data.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class JobOfferSummary(
    val caringTypeCodeList: List<String>,
    val createdAt: String,
    val dayTypeCodeList: List<String>,
    val endDate: Int,
    val endTime: String,
    val isLiked: Boolean,
    val isMatched: Boolean,
    val jobOfferId: Int,
    val neighborhood: String,
    val salary: Int,
    val salaryTypeCode: String,
    val startDate: Int,
    val startTime: String,
    val title: String
)
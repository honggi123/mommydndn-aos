package com.mommydndn.app.data.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NetworkNearestCareJobOpening(
    @SerialName("jobOfferId")
    val id: Long,
    @SerialName("neighborhood")
    val neighborhoodName: String,
    @SerialName("salaryTypeCode")
    val payPeriod: NetworkPayPeriod,
    @SerialName("caringTypeCode")
    val careType: NetworkCareType,
    @SerialName("salary")
    val pay: Int,
    val title: String
)
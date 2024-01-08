package com.mommydndn.app.data.network.feature.care.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class SalaryTypeApiModel {
    @SerialName("HOURLY")
    HOURLY,

    @SerialName("DAILY")
    DAILY,

    @SerialName("WEEKLY")
    WEEKLY,

    @SerialName("MONTHLY")
    MONTHLY,

    @SerialName("NEGOTIATION")
    NEGOTIATION
}
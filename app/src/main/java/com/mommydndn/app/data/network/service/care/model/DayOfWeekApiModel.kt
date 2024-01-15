package com.mommydndn.app.data.network.service.care.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class DayOfWeekApiModel {
    @SerialName("MON") MON,
    @SerialName("TUE") TUE,
    @SerialName("WED") WED,
    @SerialName("THU") THU,
    @SerialName("FRI") FRI,
    @SerialName("SAT") SAT,
    @SerialName("SUN") SUN
}


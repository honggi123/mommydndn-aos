package com.mommydndn.app.data.network.feature.care.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class WorkPeriodTypeApiModel {
    @SerialName("ONETIME")
    ONE_TIME,

    @SerialName("REGULAR")
    REGULAR
}
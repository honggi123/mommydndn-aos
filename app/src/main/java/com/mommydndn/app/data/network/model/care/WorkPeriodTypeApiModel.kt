package com.mommydndn.app.data.network.model.care

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class WorkPeriodTypeApiModel {
    @SerialName("ONETIME")
    ONE_TIME,

    @SerialName("REGULAR")
    REGULAR
}
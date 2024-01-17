package com.mommydndn.app.data.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class NetworkCareType {
    @SerialName("PARENTING") CHILD_CARE,
    @SerialName("NURSING") SENIOR_CARE,
    @SerialName("SCHOOL") SCHOOL_TRANSPORTATION,
    @SerialName("HOUSEKEEPING") HOUSEKEEPING,
}
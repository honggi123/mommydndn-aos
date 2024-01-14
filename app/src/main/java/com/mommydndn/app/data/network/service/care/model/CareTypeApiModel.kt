package com.mommydndn.app.data.network.service.care.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class CareTypeApiModel {
    @SerialName("PARENTING")
    SENIOR_CARE,

    @SerialName("NURSING")
    NURSING,

    @SerialName("SCHOOL")
    SCHOOL_TRANSPORTATION,

    @SerialName("HOUSEKEEPING")
    HOUSEKEEPING
}

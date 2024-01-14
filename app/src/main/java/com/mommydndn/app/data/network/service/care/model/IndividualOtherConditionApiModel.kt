package com.mommydndn.app.data.network.service.care.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class IndividualOtherConditionApiModel {
    @SerialName("PET")
    PET,

    @SerialName("CCTV")
    CCTV,

    @SerialName("RESIDENCE")
    OCCUPANCT,

    @SerialName("NON_SMOKER")
    NON_SMOKER,

    @SerialName("NO_RELIGION")
    NO_RELIGION
}
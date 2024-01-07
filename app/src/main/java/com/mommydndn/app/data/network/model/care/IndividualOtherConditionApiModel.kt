package com.mommydndn.app.data.network.model.care

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
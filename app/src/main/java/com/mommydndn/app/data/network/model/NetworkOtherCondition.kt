package com.mommydndn.app.data.network.model

import kotlinx.serialization.Serializable

@Serializable
enum class NetworkWorkerOtherCondition {
    PET,
    CCTV,
    RESIDENCE,
    NON_SMOKER,
    NO_RELIGION
}

@Serializable
enum class NetworkAgencyOtherCondition {
    AS
}
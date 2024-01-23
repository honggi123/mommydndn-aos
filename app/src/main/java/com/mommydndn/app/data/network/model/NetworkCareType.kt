package com.mommydndn.app.data.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class NetworkCareType {
    @SerialName("PARENTING") ChildCare,
    @SerialName("NURSING") SeniorCare,
    @SerialName("SCHOOL") SchoolTransportation,
    @SerialName("HOUSEKEEPING") Housekeeping,
}
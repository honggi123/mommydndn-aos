package com.mommydndn.app.data.network.service.user.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
@Serializable
enum class UserTypeApiModel {
    @SerialName("INDIVIDUAL")
    INDIVIDUAL,

    @SerialName("COMPANY")
    AGENCY
}

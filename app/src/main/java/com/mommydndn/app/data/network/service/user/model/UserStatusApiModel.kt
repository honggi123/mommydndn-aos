package com.mommydndn.app.data.network.service.user.model

import kotlinx.serialization.SerialName

enum class UserStatusApiModel {
    @SerialName("ACTIVE")
    ACTIVE,

    @SerialName("WITHDRAWN")
    WITHDRAWN,

    @SerialName("BANNED")
    BANNED
}
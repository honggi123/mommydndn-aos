package com.mommydndn.app.data.network.service.user.model

import kotlinx.serialization.SerialName

enum class OAuthProviderApiModel {
    @SerialName("ACTIVE")
    GOOGLE,

    @SerialName("APPLE")
    APPLE,

    @SerialName("NAVER")
    NAVER,

    @SerialName("KAKAO")
    KAKAO
}
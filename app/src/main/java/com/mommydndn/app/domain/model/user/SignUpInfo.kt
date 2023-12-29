package com.mommydndn.app.domain.model.user

data class SignUpInfo(
    val accessToken: String? = "",
    val oAuthType: OAuthType? = null,
    val userType: UserType? = null,
    val emdId: Int? = 0
)


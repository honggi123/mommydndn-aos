package com.mommydndn.app.domain.model.user

import com.mommydndn.app.domain.model.UserType

data class SignUpInfo(
    val accessToken: String? = "",
    val OAuthProvider: OAuthProvider? = null,
    val userType: UserType? = null,
    val emdId: Int? = 0
)
package com.mommydndn.app.data.model.user

import com.mommydndn.app.domain.model.user.OAuthProvider
import com.mommydndn.app.domain.model.user.UserType

data class SignUpInfo(
    val accessToken: String? = "",
    val OAuthProvider: OAuthProvider? = null,
    val userType: UserType? = null,
    val emdId: Int? = 0
)
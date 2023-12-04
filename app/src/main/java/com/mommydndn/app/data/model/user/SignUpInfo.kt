package com.mommydndn.app.data.model.user

import com.mommydndn.app.domain.model.user.OAuthType
import com.mommydndn.app.domain.model.user.UserType

data class SignUpInfo(
    val accessToken: String? = "",
    val oAuthType: OAuthType? = null,
    val userType: UserType? = null,
    val emdId: Int? = 0
)

fun SignUpInfo?.canSignUp(): Boolean {
    return this != null && oAuthType != null && accessToken.isNullOrBlank() && userType != null && emdId != null
}
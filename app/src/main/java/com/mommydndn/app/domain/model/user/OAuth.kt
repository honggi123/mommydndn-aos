package com.mommydndn.app.domain.model.user

enum class OAuthProvider {
    NAVER,
    KAKAO,
    GOOGLE
}

data class OAuthToken(
    val accessToken: String,
    val refreshToken: String,
)

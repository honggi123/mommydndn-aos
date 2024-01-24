package com.mommydndn.app.domain.model

data class User(
    val id: Long,
    val type: UserType,
    val profileImageUrl: String?,
    val name: String,
    val nickname: String,
    val createdAt: Long,
)

enum class UserType {
    Individual, Agency
}

enum class OAuthProvider {
    Naver, Kakao, Google
}

data class OAuthToken(
    val accessToken: String,
    val refreshToken: String,
)
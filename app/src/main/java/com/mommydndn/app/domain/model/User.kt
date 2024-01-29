package com.mommydndn.app.domain.model

import java.time.LocalDate
import java.util.Date

data class User(
    val id: String,
    val email: String,
    val profileImageUrl: String?,
    val name: String,
    val nickname: String,
    val neighborhood: Neighborhood,
    val neighborhoodVicinityLevel: NeighborhoodVicinityLevel,
    val nearbyNeighborhoods: Map<NeighborhoodVicinityLevel, List<Neighborhood>>,
    val status: UserStatus,
    val gender: Gender,
    val age: Int,
    val birthday: LocalDate,
    val isDndnCertified: Boolean,
    val verifications: List<Verification>,
    val reviews: List<Review>,
    // TODO: 구인, 구직 또는 업체 글 복수
    val jobApplicationId: String?,
    val jobPostingId: String?,
    val agencyId: String?,
    val dndnScore: Double,
    val matchingCount: Int,
    val reviewsCount: Int,
    val responseRate: Int,
    val createdAt: Date,
)

enum class UserType {
    Individual, Agency
}

enum class UserStatus {
    Active, Withdrawn, Banned
}

enum class OAuthProvider {
    Naver, Kakao, Google
}

data class OAuthToken(
    val accessToken: String,
    val refreshToken: String,
)
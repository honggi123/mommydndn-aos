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
    val nearbyNeighborhoodDistance: NearbyNeighborhoodDistance,
    val nearbyNeighborhoods: Map<NearbyNeighborhoodDistance, List<Neighborhood>>,
    val status: UserStatus,
    val gender: Gender,
    val age: Int,
    val birthday: LocalDate,
    val dndnCertified: Boolean,
    val verifications: List<Verification>,
    val reviews: List<Review>,
    // TODO
    val jobApplicationId: String?,
    val jobPostingId: String?,
    val agencyId: String?,
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
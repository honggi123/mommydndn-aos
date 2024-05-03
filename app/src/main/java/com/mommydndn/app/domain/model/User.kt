package com.mommydndn.app.domain.model

import java.time.LocalDate
import java.util.Date

// TODO: 구인, 구직, 업체 글 복수형?, Optional Fields
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
    val jobApplicationId: String?,
    val jobPostingId: List<String>,
    val agencyId: String?,
    val dndnScore: Double,
    val matchingCount: Int,
    val reviewsCount: Int,
    val responseRate: Int,
    val createdAt: Date,
) {
    constructor() : this(
        id = "",
        email = "",
        profileImageUrl = null,
        name = "",
        nickname = "",
        neighborhood = Neighborhood(),
        neighborhoodVicinityLevel = NeighborhoodVicinityLevel.VeryDistant,
        nearbyNeighborhoods = emptyMap(),
        status = UserStatus.Active,
        gender = Gender.Male,
        age = 0,
        birthday = LocalDate.MIN,
        isDndnCertified = false,
        verifications = emptyList(),
        reviews = emptyList(),
        jobApplicationId = null,
        jobPostingId = emptyList(),
        agencyId = null,
        dndnScore = 0.0,
        matchingCount = 0,
        reviewsCount = 0,
        responseRate = 0,
        createdAt = Date()
    )
}

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
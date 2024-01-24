package com.mommydndn.app.domain.model

data class CareWorker(
    val id: Long,
    val profileImageUrl: String,
    val nickname: String,
    val neighborhoodName: String,
    val dndnCertified: Boolean,
    val dndnScore: Double,
    val careTypes: List<CareType>,
    val ageRange: String, // TODO
    val gender: Gender,
    val matchingCount: Int,
    val reviewsCount: Int,
    val responseRate: Int,
)

enum class Gender {
    Male, Female,
}

data class CareWorkerDetails(
    val id: Long,
    val worker: CareWorker,
    val bio: String,
    val verifications: List<Verification>,
    val otherConditions: List<CareWorkerOtherCondition>,
    val careTypes: List<CareType>,
)
package com.mommydndn.app.domain.model

data class Verification(
    val id: Long,
    val type: VerificationType,
    val name: String,
    val status: VerificationStatus,
    val updatedAt: Long,
)

// TODO: 네이밍
enum class VerificationType {
    Identity,
    Mother,
    Qualification, // ?
    Career,
    School,
    Health, // ?
    Teacher,
    BusinessLicense
}

enum class VerificationStatus {
    InProgress, Accepted, Rejected, Deleted
}
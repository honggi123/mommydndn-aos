package com.mommydndn.app.domain.model

import java.util.Date

data class Verification(
    val id: String,
    val type: VerificationType,
    val name: String,
    val status: VerificationStatus,
    val updatedAt: Date,
)

enum class VerificationType {
    Identity,          // 신원 확인
    Maternity,         // 출산 여부 확인
    Certification,     // 자격증 확인
    Career,            // 경력 확인
    Education,         // 학력 확인
    Health,            // 건강 상태 확인
    TeachingQualification,  // 교사 자격 확인
    BusinessLicense    // 사업 라이선스 확인
}

enum class VerificationStatus {
    InProgress, Accepted, Rejected, Deleted
}
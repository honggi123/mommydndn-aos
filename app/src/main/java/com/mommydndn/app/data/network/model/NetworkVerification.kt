package com.mommydndn.app.data.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NetworkVerification(
    @SerialName("userCertificationId") val id: Long,
    @SerialName("certificationTypeCode") val type: NetworkVerificationType,
    @SerialName("certificationName") val name: String,
    @SerialName("certStatusTypeCode") val status: NetworkVerificationStatus,
    @SerialName("certImageId") val imageId: Long,
    val updatedAt: Long,
)

enum class NetworkVerificationType {
    IDENTITY, MOTHER, QUALIFICATION, CAREER, SCHOOL, HEALTH, TEACHER, BUSINESS_LICENSE
}

enum class NetworkVerificationStatus {
    WAITING, COMPLETED, REJECTED, DELETED
}
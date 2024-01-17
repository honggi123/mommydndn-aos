package com.mommydndn.app.data.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NetworkCertification(
    @SerialName("userCertificationId") val id: Long,
    @SerialName("certificationTypeCode") val type: NetworkCertificationType,
    @SerialName("certificationName") val name: String,
    @SerialName("certStatusTypeCode") val status: NetworkCertificationStatus,
    @SerialName("certImageId") val imageId: Long,
    val updatedAt: Long,
)

enum class NetworkCertificationType

enum class NetworkCertificationStatus
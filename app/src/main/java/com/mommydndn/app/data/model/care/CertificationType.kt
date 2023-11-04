package com.mommydndn.app.data.model.care

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

enum class CertificationType {
    IDENTITY,
    MOTHER,
    QUALIFICATION,
    CAREER,
    SCHOOL,
    HEALTH,
    TEACHER
}

object CertificationTypeSerializer : KSerializer<CertificationType> {
    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor("CertificationType", PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: CertificationType) {
        encoder.encodeString(value.name)
    }

    override fun deserialize(decoder: Decoder): CertificationType {
        return CertificationType.valueOf(decoder.decodeString())
    }
}
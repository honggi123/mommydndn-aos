package com.mommydndn.app.data.model

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

enum class UserType(val apiValue: String) {
    INDIVIDUAL("INDIVIDUAL"),
    COMPANY("COMPANY")
}

object UserTypeSerializer : KSerializer<UserType> {
    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor("UserType", PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: UserType) {
        encoder.encodeString(value.name)
    }

    override fun deserialize(decoder: Decoder): UserType {
        return UserType.values().find { it.apiValue == decoder.decodeString() }
            ?: throw IllegalArgumentException("Invalid UserType value")
    }
}
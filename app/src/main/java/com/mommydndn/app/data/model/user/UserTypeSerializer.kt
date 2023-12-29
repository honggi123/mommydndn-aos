package com.mommydndn.app.data.model.user

import com.mommydndn.app.domain.model.user.UserType
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

object UserTypeSerializer : KSerializer<UserType> {

    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor(
        serialName = "UserType",
        kind = PrimitiveKind.STRING
    )

    override fun serialize(encoder: Encoder, value: UserType) {
        encoder.encodeString(value.name)
    }

    override fun deserialize(decoder: Decoder): UserType {
        return UserType.valueOf(decoder.decodeString())
    }
}
package com.mommydndn.app.data.network.serialization

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import java.time.DayOfWeek

object DayOfWeekSerializer : KSerializer<DayOfWeek> {

    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor(
        serialName = "DayOfWeek",
        kind = PrimitiveKind.STRING
    )

    override fun deserialize(decoder: Decoder): DayOfWeek {
        val displayName = decoder.decodeString()

        return DayOfWeek.entries.first {
            it.name.take(3) == displayName
        }
    }

    override fun serialize(encoder: Encoder, value: DayOfWeek) {
        encoder.encodeString(value.name.take(3))
    }
}
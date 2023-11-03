package com.mommydndn.app.data.model.care

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

enum class EtcConditionType(val value: String) {
    PET("애완 동물 가능"),
    CCTV("CCTV 가능"),
    RESIDENCE("입주 가능"),
    NON_SMOKER("비 흡연자"),
    NO_RELIGION("무교")
}

object EtcConditionTypeSerializer : KSerializer<EtcConditionType> {
    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor("EtcConditionType", PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: EtcConditionType) {
        encoder.encodeString(value.name)
    }

    override fun deserialize(decoder: Decoder): EtcConditionType {
        return EtcConditionType.valueOf(decoder.decodeString())
    }
}
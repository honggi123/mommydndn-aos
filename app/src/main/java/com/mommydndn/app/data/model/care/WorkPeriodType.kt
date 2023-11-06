package com.mommydndn.app.data.model.care

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

enum class WorkPeriodType(val value: String) {
    ONETIME("단기"),
    REGULAR("정기")
}

data class WorkPeriodTypeItem(
    val workPeriodType: WorkPeriodType,
    var isSelected: Boolean = false
)

object WorkPeriodTypeSerializer : KSerializer<WorkPeriodType> {
    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor("WorkPeriodType", PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: WorkPeriodType) {
        encoder.encodeString(value.name)
    }

    override fun deserialize(decoder: Decoder): WorkPeriodType {
        return WorkPeriodType.valueOf(decoder.decodeString())
    }
}
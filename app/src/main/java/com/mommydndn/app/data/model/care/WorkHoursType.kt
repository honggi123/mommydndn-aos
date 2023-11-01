package com.mommydndn.app.data.model.care

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

enum class WorkHoursType(val value: String) {
    ONETIME("단기"),
    REGULAR("정기")
}

data class WorkHoursTypeItem(
    val workHoursType: WorkHoursType,
    var isSelected: Boolean = false
)

object WorkHourTypeSerializer : KSerializer<WorkHoursType> {
    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor("WorkHoursType", PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: WorkHoursType) {
        encoder.encodeString(value.name)
    }

    override fun deserialize(decoder: Decoder): WorkHoursType {
        return WorkHoursType.valueOf(decoder.decodeString())
    }
}
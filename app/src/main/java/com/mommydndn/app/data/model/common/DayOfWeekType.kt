package com.mommydndn.app.data.model.common

import com.mommydndn.app.data.model.care.SalaryType
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

enum class DayOfWeekType(val displayingName: String, var isSelected: Boolean = false) {
    MON("월"),
    TUE("화"),
    WED("수"),
    THU("목"),
    FRI("금"),
    SAT("토"),
    SUN("일");
}

data class DayOfWeekItem(
    val type: DayOfWeekType,
    var isSelected: Boolean = false
)

object DayOfWeekTypeSerializer : KSerializer<DayOfWeekType> {
    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor("DayOfWeekType", PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: DayOfWeekType) {
        encoder.encodeString(value.name)
    }

    override fun deserialize(decoder: Decoder): DayOfWeekType {
        return DayOfWeekType.valueOf(decoder.decodeString())
    }
}


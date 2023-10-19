package com.mommydndn.app.data.model.care

import com.mommydndn.app.data.model.user.UserType
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

enum class SalaryType(val value: String) {
    HOURLY("시급"),
    DAILY("일급"),
    WEEKLY("주급"),
    MONTHLY("월급"),
    NEGOTIATION("협의")
}

data class SalaryTypeItem(
    val salaryType: SalaryType,
    var isSelected: Boolean = false
)

object SalaryTypeSerializer : KSerializer<SalaryType> {
    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor("SalaryType", PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: SalaryType) {
        encoder.encodeString(value.name)
    }

    override fun deserialize(decoder: Decoder): SalaryType {
        return SalaryType.valueOf(decoder.decodeString())
    }
}
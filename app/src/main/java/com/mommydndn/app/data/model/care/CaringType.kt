package com.mommydndn.app.data.model.care

import com.mommydndn.app.data.model.user.UserType
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

enum class CaringType(val value: String) {
    PARENTING("육아"),
    NURSING("요양"),
    SCHOOL("등하원"),
    HOUSEKEEPING("가사")
}

data class CaringTypeItem(
    val caringType: CaringType,
    var isSelected: Boolean = false
)

object CaringTypeSerializer : KSerializer<CaringType> {
    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor("CaringType", PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: CaringType) {
        encoder.encodeString(value.name)
    }

    override fun deserialize(decoder: Decoder): CaringType {
        return CaringType.valueOf(decoder.decodeString())
    }
}

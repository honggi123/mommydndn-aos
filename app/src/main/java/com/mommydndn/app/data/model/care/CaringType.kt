package com.mommydndn.app.data.model.care

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

enum class CaringType(
    val value: String,
    var isSelected: Boolean = false
) {
    PARENTING("육아"),
    NURSING("요양"),
    SCHOOL("등하원"),
    HOUSEKEEPING("가사")
}


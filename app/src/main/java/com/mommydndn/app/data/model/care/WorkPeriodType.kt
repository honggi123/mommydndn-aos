package com.mommydndn.app.data.model.care

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

enum class WorkPeriodType(val value: String,var isSelected: Boolean = false) {
    ONETIME("1회성"),
    REGULAR("정기")
}

data class WorkPeriodTypeItem(
    val workPeriodType: WorkPeriodType?,
    var isSelected: Boolean = false
)


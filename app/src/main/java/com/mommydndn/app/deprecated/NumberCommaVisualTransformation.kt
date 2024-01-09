package com.mommydndn.app.deprecated

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

class NumberCommaVisualTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val amount = if (text.text.isNullOrBlank()) ""
        else NumberUtils.getPriceString(text.text.toInt())

        return TransformedText(
            text = AnnotatedString(if (text.isEmpty()) "" else amount),
            offsetMapping = object : OffsetMapping {
                override fun originalToTransformed(offset: Int): Int {
                    val commas = amount.count { it == ',' }
                    return offset + commas
                }

                override fun transformedToOriginal(offset: Int): Int {
                    val commas = amount.count { it == ',' }

                    return when (offset) {
                        8, 7 -> offset - 2
                        6 -> if (commas == 1) 5 else 4
                        5 -> if (commas == 1) 4 else if (commas == 2) 3 else offset
                        4, 3 -> if (commas >= 1) offset - 1 else offset
                        2 -> if (commas == 2) 1 else offset
                        else -> offset
                    }
                }
            }
        )
    }
}
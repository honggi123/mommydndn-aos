package com.mommydndn.app.data.model.common

import androidx.compose.ui.graphics.Color
import com.mommydndn.app.ui.theme.Grey100
import com.mommydndn.app.ui.theme.Grey500
import com.mommydndn.app.ui.theme.Grey600
import com.mommydndn.app.ui.theme.Salmon200
import com.mommydndn.app.ui.theme.Salmon600
import com.mommydndn.app.ui.theme.White

enum class ButtonColor(backgroundColor: Color, textColor: Color) {
    WHITE(backgroundColor = White, textColor = Grey500),
    SALMON(backgroundColor = Salmon200, textColor = Salmon600),
    GREY(backgroundColor = Grey100, textColor = Grey600),
    SALMON_FILLED(backgroundColor = Salmon600, textColor = White)
}

enum class ButtonColorType {
    WEAK,
    FILLED,
}

enum class ButtonWidth {
    LARGE,
    MEDIUM,
    SMALL
}

enum class MinMaxRange {
    MIN,
    MAX,
}

data class ButtonStyle(
    val colorType: ButtonColorType,
    val size: ButtonWidth,
    val range: MinMaxRange
) {
    val color: ButtonColor
        get() {
            return if (colorType == ButtonColorType.FILLED) {
                when (this.color) {
                    ButtonColor.SALMON -> ButtonColor.SALMON_FILLED
                    else -> this.color
                }
            } else this.color
        }
}
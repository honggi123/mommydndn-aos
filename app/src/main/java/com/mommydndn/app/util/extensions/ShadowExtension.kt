package com.mommydndn.app.util.extensions

import android.graphics.BlurMaskFilter
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


internal fun Modifier.drawShadow(
    color: Color,
    // borderRadius: Dp = 0.dp,
    blurRadius: Dp = 0.dp,
    spread: Dp = 0.dp,
    offsetY: Dp = 0.dp,
    offsetX: Dp = 0.dp,
) = drawBehind {
    val shadowColor = color.toArgb()

    drawIntoCanvas { canvas ->
        val paint = Paint()

        val nativePaint = paint.asFrameworkPaint()

        nativePaint.color = android.graphics.Color.TRANSPARENT

        nativePaint.setShadowLayer(
            blurRadius.toPx(),
            offsetX.toPx(),
            offsetY.toPx(),
            shadowColor
        )

        if (blurRadius > 0.dp) {
            nativePaint.maskFilter = BlurMaskFilter(
                blurRadius.toPx(),
                BlurMaskFilter.Blur.NORMAL
            )
        }

        val spreadPx = spread.toPx()

        canvas.drawRoundRect(
            left = 0F - spreadPx + offsetX.toPx(),
            top = 0F - spreadPx + offsetY.toPx(),
            right = size.width + spreadPx,
            bottom = size.height + spreadPx,
            radiusX = 0F, // borderRadius.toPx(),
            radiusY = 0F, // borderRadius.toPx(),
            paint = paint
        )
    }
}
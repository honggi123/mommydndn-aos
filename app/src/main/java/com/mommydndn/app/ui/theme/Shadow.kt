package com.mommydndn.app.ui.theme

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.mommydndn.app.utils.extensions.coloredShadow
import com.mommydndn.app.utils.extensions.drawShadow

val shadow100 = Modifier.coloredShadow(
    color = Color(0xff1D34610D),
    offsetX = 0.dp,
    offsetY = 1.dp,
    blurRadius = 2.dp,
    spread = 0f
)

val shadow200 = Modifier
    .coloredShadow(
        color = Color(0xff1D34611A),
        offsetX = 0.dp,
        offsetY = 1.dp,
        blurRadius = 3.dp,
        spread = 0f
    )
    .coloredShadow(
        color = Color(0xff1D34610F),
        offsetX = 0.dp,
        offsetY = 1.dp,
        blurRadius = 2.dp,
        spread = 0f
    )

val shadow300 = Modifier
    .coloredShadow(
        color = Color(0xff1D34611A),
        offsetX = 0.dp,
        offsetY = 4.dp,
        blurRadius = 8.dp,
        spread = -2f
    )
    .coloredShadow(
        color = Color(0xff1D34610F),
        offsetX = 0.dp,
        offsetY = 2.dp,
        blurRadius = 4.dp,
        spread = -2f
    )

val shadow400 = Modifier
    .coloredShadow(
        color = Color(0xff1D346114),
        offsetX = 0.dp,
        offsetY = 12.dp,
        blurRadius = 16.dp,
        spread = -4f
    )
    .coloredShadow(
        color = Color(0xff1D346108),
        offsetX = 0.dp,
        offsetY = 4.dp,
        blurRadius = 6.dp,
        spread = -2f
    )

val Shadow500 = Modifier
    .drawShadow(
        color = Color(0x141D3461),
        blurRadius = 24.dp,
        spread = 6.dp,
        offsetX = 0.dp,
        offsetY = 20.dp,
    )
    .drawShadow(
        color = Color(0x081D3461),
        blurRadius = 8.dp,
        spread = 4.dp,
        offsetX = 0.dp,
        offsetY = 8.dp,
    )

val shadow600 = Modifier.coloredShadow(
    color = Color(0xff1D34612E),
    offsetX = 0.dp,
    offsetY = 24.dp,
    blurRadius = 48.dp,
    spread = -12f
)

val Shadow700 = Modifier.coloredShadow(
    color = Color(0x241D3461),
    offsetX = 0.dp,
    offsetY = 32.dp,
    blurRadius = 54.dp,
    spread = -12f
)

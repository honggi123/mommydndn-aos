package com.mommydndn.app.ui.care.details.components

import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material.Divider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.mommydndn.app.ui.theme.Grey50

internal fun LazyListScope.divider(
    modifier: Modifier = Modifier,
    color: Color = Grey50,
    thickness: Dp = 20.dp,
) {
    item {
        Divider(
            modifier = modifier,
            color = color,
            thickness = thickness
        )
    }
}
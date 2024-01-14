package com.mommydndn.app.ui.components.chip

import androidx.compose.foundation.BorderStroke
import androidx.compose.material.Chip
import androidx.compose.material.ChipColors
import androidx.compose.material.ChipDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.material.ripple.RippleTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.mommydndn.app.ui.theme.Grey100
import com.mommydndn.app.ui.theme.Grey700
import com.mommydndn.app.ui.theme.Salmon600
import com.mommydndn.app.ui.theme.White
import com.mommydndn.app.ui.theme.caption200

@Composable
fun ClickableChip(
    text: String,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    textColor: Color = if (selected) {
        White
    } else {
        Grey700
    },
    chipColors: ChipColors = ChipDefaults.chipColors(
        backgroundColor = if (selected) {
            Salmon600
        } else {
            White
        }
    ),
) {
    Chip(
        onClick = onClick,
        modifier = modifier,
        border = BorderStroke(
            width = 1.dp,
            brush = SolidColor(Grey100),
        ).takeIf { !selected },
        colors = chipColors,
    ) {
        Text(
            text = text,
            modifier = Modifier.align(Alignment.CenterVertically),
            color = textColor,
            style = MaterialTheme.typography.caption200.merge(
                fontWeight = FontWeight.Medium,
            )
        )
    }
}

private object NoRippleTheme : RippleTheme {
    @Composable
    override fun defaultColor(): Color = Color.Unspecified

    @Composable
    override fun rippleAlpha(): RippleAlpha = RippleAlpha(
        draggedAlpha = 0F,
        focusedAlpha = 0F,
        hoveredAlpha = 0F,
        pressedAlpha = 0F,
    )
}
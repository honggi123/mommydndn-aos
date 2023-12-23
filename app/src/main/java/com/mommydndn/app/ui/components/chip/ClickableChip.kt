package com.mommydndn.app.ui.components.chip

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Chip
import androidx.compose.material.ChipDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.material.ripple.RippleTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
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
) {
    val backgroundColor = if (selected) {
        Salmon600
    } else {
        White
    }

    val textColor = if (selected) {
        White
    } else {
        Grey700
    }

    val border = if (selected) {
        null
    } else {
        BorderStroke(
            width = 1.dp,
            brush = SolidColor(Grey100),
        )
    }

    CompositionLocalProvider(LocalRippleTheme.provides(NoRippleTheme)) {
        Chip(
            onClick = onClick,
            modifier = modifier,
            border = border,
            colors = ChipDefaults.chipColors(
                backgroundColor = backgroundColor,
            ),
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = text,
                    style = MaterialTheme.typography.caption200.copy(
                        fontWeight = FontWeight.Medium,
                        color = textColor
                    )
                )
            }
        }
    }
}

private object NoRippleTheme : RippleTheme {
    @Composable
    override fun defaultColor(): Color = Color.Unspecified

    @Composable
    override fun rippleAlpha(): RippleAlpha = RippleAlpha(0F, 0F, 0F, 0F)
}

@Preview
@Composable
fun PreviewClickableChip_NotSelected() {
    ClickableChip(
        text = "텍스트",
        selected = false,
        onClick = {},
    )
}

@Preview
@Composable
fun PreviewClickableChip_Selected() {
    ClickableChip(
        text = "텍스트",
        selected = true,
        onClick = {},
    )
}
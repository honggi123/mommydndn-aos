package com.mommydndn.app.ui.components.chip

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Chip
import androidx.compose.material.ChipDefaults
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mommydndn.app.ui.theme.Grey100
import com.mommydndn.app.ui.theme.Grey700
import com.mommydndn.app.ui.theme.Salmon600
import com.mommydndn.app.ui.theme.White
import com.mommydndn.app.ui.theme.caption200

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ClickableChip(
    modifier: Modifier = Modifier,
    selected: Boolean = false,
    text: String = "",
    onClick: () -> Unit = {}
) {

    val chipColor = if (selected) Salmon600 else White
    val textColor = if (selected) White else Grey700
    val border = if (selected) null else BorderStroke(width = 1.dp, brush = SolidColor(Grey100))
    Chip(
        modifier = modifier,
        border = border,
        colors = ChipDefaults.chipColors(
            backgroundColor = chipColor,
        ),
        onClick = {
            onClick()
        },
        content = {
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
        })
}

@Preview
@Composable
fun chipPreview() {
    ClickableChip(
        selected = true,
        text = "텍스트"
    )
}


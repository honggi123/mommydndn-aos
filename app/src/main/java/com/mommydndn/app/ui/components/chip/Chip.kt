package com.mommydndn.app.ui.components.chip

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Chip
import androidx.compose.material.ChipDefaults
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.mommydndn.app.ui.theme.Grey700
import com.mommydndn.app.ui.theme.Salmon200
import com.mommydndn.app.ui.theme.Salmon600
import com.mommydndn.app.ui.theme.White
import com.mommydndn.app.ui.theme.caption200

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Chip(
    selected: Boolean = true,
    text: String = "",
    onClick: () -> Unit = {}
) {

    val chipColor = if (selected) Salmon600 else White
    val textColor = if (selected) White else Grey700

    Chip(
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
                        color = textColor,
                        platformStyle = PlatformTextStyle(
                            includeFontPadding = false
                        )
                    )
                )
            }
        })
}

@Preview
@Composable
fun chipPreview() {
    Chip(
        selected = true,
        text = "텍스트"
    )
}


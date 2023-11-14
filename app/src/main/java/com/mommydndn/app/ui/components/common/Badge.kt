package com.mommydndn.app.ui.components.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mommydndn.app.data.model.common.BadgeColorType
import com.mommydndn.app.ui.theme.Grey100
import com.mommydndn.app.ui.theme.Grey600
import com.mommydndn.app.ui.theme.MommydndnaosTheme
import com.mommydndn.app.ui.theme.Shapes
import com.mommydndn.app.ui.theme.caption100

@Composable
fun Badge(
    modifier: Modifier = Modifier,
    text: String = "",
    colorType: BadgeColorType
) {
    val textColor = when (colorType) {
        BadgeColorType.GREEN -> Color(0xFF4ABC56)
        BadgeColorType.ORANGE -> Color(0xFFF6942B)
        BadgeColorType.BLUE -> Color(0xFF2B64F6)
        BadgeColorType.BLACK -> Grey600
    }

    val backgroundColor = when (colorType) {
        BadgeColorType.GREEN -> Color(0xFFEEFBEA)
        BadgeColorType.ORANGE -> Color(0xFFFFF7EE)
        BadgeColorType.BLUE -> Color(0xFFEEF7FF)
        BadgeColorType.BLACK -> Grey100
    }

    Box(
        modifier = modifier
            .background(shape = RoundedCornerShape(6.dp), color = backgroundColor),
        contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = Modifier.padding(start = 8.dp, end = 8.dp, top = 4.dp, bottom = 4.dp),
            text = text,
            style = MaterialTheme.typography.caption100.copy(
                fontWeight = FontWeight.Medium,
                color = textColor,
                textAlign = TextAlign.Center
            ),
            maxLines = 1
        )
    }
}

@Preview
@Composable
fun previewBadge() {
    MommydndnaosTheme {
        Badge(colorType = BadgeColorType.GREEN, text = "text")
    }
}
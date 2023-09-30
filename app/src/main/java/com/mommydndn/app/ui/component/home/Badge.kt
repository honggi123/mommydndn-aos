package com.mommydndn.app.ui.component.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mommydndn.app.data.model.BannerColorType
import com.mommydndn.app.ui.theme.Grey100
import com.mommydndn.app.ui.theme.Grey600
import com.mommydndn.app.ui.theme.MommydndnaosTheme
import com.mommydndn.app.ui.theme.Shapes
import com.mommydndn.app.ui.theme.White
import com.mommydndn.app.ui.theme.caption100
import com.mommydndn.app.ui.theme.caption200

@Composable
fun Badge(
    text: String = "",
    colorType: BannerColorType
) {
    val textColor = when (colorType) {
        BannerColorType.GREEN -> Color(0xFF4ABC56)
        BannerColorType.ORANGE -> Color(0xFFF6942B)
        BannerColorType.BLUE -> Color(0xFF2B64F6)
        BannerColorType.BLACK -> Grey600
    }

    val backgroundColor = when (colorType) {
        BannerColorType.GREEN -> Color(0xFFEEFBEA)
        BannerColorType.ORANGE -> Color(0xFFFFF7EE)
        BannerColorType.BLUE -> Color(0xFFEEF7FF)
        BannerColorType.BLACK -> Grey100
    }

    Box(
        modifier = Modifier
            .background(shape = Shapes.large, color = backgroundColor),
        contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = Modifier.padding(start = 8.dp, end = 8.dp, top = 4.dp, bottom = 4.dp),
            text = text,
            style = MaterialTheme.typography.caption100.copy(
                fontWeight = FontWeight.Medium,
                color = textColor,
                platformStyle = PlatformTextStyle(
                    includeFontPadding = false
                ),
                textAlign = TextAlign.Center
            )
        )
    }
}

@Preview
@Composable
fun previewBadge() {
    MommydndnaosTheme {
        Badge(colorType = BannerColorType.GREEN, text = "text")
    }
}
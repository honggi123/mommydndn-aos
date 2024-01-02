package com.mommydndn.app.feature.components

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
import com.mommydndn.app.ui.theme.Green100
import com.mommydndn.app.ui.theme.Green600
import com.mommydndn.app.ui.theme.caption200

@Composable
fun TagLabel(
    text: String,
    textColor: Color,
    backgroundColor: Color,
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        Text(
            text = text,
            modifier = Modifier
                .background(
                    color = backgroundColor, shape = RoundedCornerShape(6.dp)
                )
                .padding(horizontal = 8.dp, vertical = 4.dp),
            color = textColor,
            textAlign = TextAlign.Center,
            maxLines = 1,
            style = MaterialTheme.typography.caption200.merge(
                fontWeight = FontWeight.Medium,
            ),
        )
    }
}

//    val textColor = when (colorType) {
//        BadgeColorType.GREEN -> Color(0xFF4ABC56)
//        BadgeColorType.ORANGE -> Color(0xFFF6942B)
//        BadgeColorType.BLUE -> Color(0xFF2B64F6)
//        BadgeColorType.BLACK -> Grey600
//    }
//
//    val backgroundColor = when (colorType) {
//        BadgeColorType.GREEN -> Color(0xFFEEFBEA)
//        BadgeColorType.ORANGE -> Color(0xFFFFF7EE)
//        BadgeColorType.BLUE -> Color(0xFFEEF7FF)
//        BadgeColorType.BLACK -> Grey100
//    }

@Preview
@Composable
private fun TagLabel_Preview() {
    TagLabel(
        text = "30대 여성",
        textColor = Green600,
        backgroundColor = Green100,
        modifier = Modifier,
    )
}
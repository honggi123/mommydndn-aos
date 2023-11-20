package com.mommydndn.app.ui.components.box

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.mommydndn.app.ui.theme.Grey800
import com.mommydndn.app.ui.theme.White
import com.mommydndn.app.ui.theme.caption100
import com.mommydndn.app.ui.theme.caption200
import com.mommydndn.app.ui.theme.paragraph400

@Composable
fun UserIntroductionBox(
    modifier: Modifier = Modifier,
    title: String,
    content: String = "",
) {
    Box(
        modifier = modifier
            .width(390.dp)
            .background(White),
    ) {
        Box(
            modifier = modifier
                .fillMaxWidth()
                .clip(shape = RoundedCornerShape(12.dp))
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(
                            Color(0xFFE04179).copy(alpha = 0.2f),
                            Color(0xFFE38773).copy(alpha = 0.2f),
                            Color(0xFFFFC125).copy(alpha = 0.2f)
                        ),
                    )
                )
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.Top),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(
                    horizontal = 24.dp,
                    vertical = 16.dp
                )
            ) {
                Text(
                    text = title,
                    color = Grey800,
                    style = MaterialTheme.typography.caption200.copy(
                        fontWeight = FontWeight.Medium
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(align = Alignment.CenterVertically)
                )
                Text(
                    text = content,
                    color = Grey800,
                    style = MaterialTheme.typography.caption100.copy(
                        fontWeight = FontWeight.Normal
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(align = Alignment.CenterVertically)
                )
            }
        }
    }
}
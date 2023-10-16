package com.mommydndn.app.ui.components.button

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.mommydndn.app.data.model.BannerColorType
import com.mommydndn.app.ui.models.SelectButtonContent
import com.mommydndn.app.ui.theme.Grey200
import com.mommydndn.app.ui.theme.Grey600
import com.mommydndn.app.ui.theme.Salmon200
import com.mommydndn.app.ui.theme.Salmon300
import com.mommydndn.app.ui.theme.White
import com.mommydndn.app.ui.theme.caption200
import com.mommydndn.app.ui.theme.paragraph500


@Composable
fun SelectButton(
    modifier: Modifier = Modifier,
    content: SelectButtonContent
) {
    val borderColor = if (content.isSelected) Salmon300 else Grey200
    val backgroundColor = if (content.isSelected) Salmon200 else White

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .clip(shape = RoundedCornerShape(40.dp))
            .background(color = backgroundColor)
            .border(
                border = BorderStroke(1.dp, borderColor),
                shape = RoundedCornerShape(40.dp)
            )
    ) {
        Text(
            text = content.text,
            style = MaterialTheme.typography.caption200.copy(
                fontWeight = FontWeight.Medium,
                color = Grey600
            )
        )
    }
}


package com.mommydndn.app.deprecated.components.chip

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mommydndn.app.ui.theme.Grey50
import com.mommydndn.app.ui.theme.Grey500
import com.mommydndn.app.ui.theme.caption200

@Composable
fun LabelChip(
    modifier: Modifier = Modifier,
    text: String = "",
) {

    val chipColor = Grey50
    val textColor = Grey500

    Box(
        modifier = modifier
            .background(chipColor)
            .clip(shape = RoundedCornerShape(100.dp)),
    ) {
        Text(
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
            text = text,
            style = MaterialTheme.typography.caption200.copy(
                fontWeight = FontWeight.Medium,
                color = textColor
            )
        )
    }
}

@Preview
@Composable
fun LabelChipPreview() {
    LabelChip(
        text = "텍스트"
    )
}


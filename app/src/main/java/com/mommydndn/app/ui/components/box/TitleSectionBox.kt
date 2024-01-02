package com.mommydndn.app.ui.components.box

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.mommydndn.app.ui.theme.Grey500
import com.mommydndn.app.ui.theme.Grey800
import com.mommydndn.app.ui.theme.caption100
import com.mommydndn.app.ui.theme.paragraph400

@Composable
fun TitleSectionBox(
    modifier: Modifier = Modifier,
    titleText: String = "",
    badgeStringList: List<String>,
    timeText: String = ""
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(6.dp, Alignment.Top),
        modifier = modifier
            .requiredWidth(width = 342.dp)
    ) {
        Text(
            text = titleText,
            color = Grey800,
            style = MaterialTheme.typography.paragraph400.copy(
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(align = Alignment.CenterVertically)
        )
        Row(
            horizontalArrangement = Arrangement.spacedBy(3.dp, Alignment.Start)
        ) {
            badgeStringList.forEach {
                // TODO
                // TagLabel(colorType = BadgeColorType.ORANGE, text = it)
            }
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(4.dp, Alignment.Start),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = timeText,
                color = Grey500,
                style = MaterialTheme.typography.caption100.copy(
                    fontWeight = FontWeight.Normal
                ),
                modifier = Modifier
                    .wrapContentHeight(align = Alignment.CenterVertically)
            )
        }
    }
}


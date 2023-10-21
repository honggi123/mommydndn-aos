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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mommydndn.app.data.model.common.BadgeColorType
import com.mommydndn.app.ui.components.common.Badge
import com.mommydndn.app.ui.theme.Grey500
import com.mommydndn.app.ui.theme.Grey800
import com.mommydndn.app.ui.theme.caption100
import com.mommydndn.app.ui.theme.paragraph400

@Composable
fun TitleSectionBox(
    modifier: Modifier = Modifier,

) {
    Column(
        verticalArrangement = Arrangement.spacedBy(6.dp, Alignment.Top),
        modifier = modifier
            .requiredWidth(width = 342.dp)
    ) {
        Text(
            text = "2일간 풀타임으로 아이 둘 맡아주실 분 구해요. ",
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
            Badge(colorType = BadgeColorType.ORANGE, text = "육아")
            Badge(colorType = BadgeColorType.ORANGE, text = "가사")
            Badge(colorType = BadgeColorType.ORANGE, text = "등하원")
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(4.dp, Alignment.Start),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "1시간 전",
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

@Preview(widthDp = 342, heightDp = 77)
@Composable
private fun TitleSectionPreview() {
    TitleSectionBox(Modifier)
}
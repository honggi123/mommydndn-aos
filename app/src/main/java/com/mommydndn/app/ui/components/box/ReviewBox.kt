package com.mommydndn.app.ui.components.box

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mommydndn.app.R
import com.mommydndn.app.data.model.common.BadgeColorType
import com.mommydndn.app.ui.components.common.Badge
import com.mommydndn.app.ui.theme.Grey400
import com.mommydndn.app.ui.theme.Grey50
import com.mommydndn.app.ui.theme.Grey600
import com.mommydndn.app.ui.theme.caption100
import com.mommydndn.app.ui.theme.caption200

@Composable
fun ReviewBox(
    modifier: Modifier = Modifier,
    dndnScore: Int = 0,
    titleText: String = "",
    dateText: String = "",
    contentText: String = "",
    badgeStringList: List<String> = emptyList()
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(6.dp))
            .background(color = Grey50)
            .padding(
                horizontal = 16.dp,
                vertical = 12.dp
            )
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(6.dp, Alignment.Start),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = titleText,
                    color = Grey600,
                    style = MaterialTheme.typography.caption200.copy(
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier
                        .wrapContentHeight(align = Alignment.CenterVertically)
                )
                Text(
                    text = dateText,
                    color = Grey400,
                    style = MaterialTheme.typography.caption100.copy(
                        fontWeight = FontWeight.Normal
                    ),
                    modifier = Modifier
                        .wrapContentHeight(align = Alignment.CenterVertically)
                )
            }
            Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp, Alignment.Start),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(4.dp, Alignment.Start),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_muscle),
                        contentDescription = "",
                        modifier = Modifier
                            .size(size = 16.dp)
                    )
                    Text(
                        text = dndnScore.toString(),
                        color = Grey600,
                        style = MaterialTheme.typography.caption200.copy(

                        ),
                        modifier = Modifier
                            .wrapContentHeight(align = Alignment.CenterVertically)
                    )
                    badgeStringList.forEach {
                        Badge(colorType = BadgeColorType.ORANGE, text = it)
                    }
                }
            }
            Text(
                text = contentText,
                color = Grey600,
                style = MaterialTheme.typography.caption200.copy(
                    fontWeight = FontWeight.Normal
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(align = Alignment.CenterVertically)
            )
        }
    }
}

@Preview
@Composable
private fun ReviewBoxPreview() {
    ReviewBox(Modifier.fillMaxWidth())
}
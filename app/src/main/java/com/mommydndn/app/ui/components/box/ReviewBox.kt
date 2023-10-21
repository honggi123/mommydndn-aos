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
    modifier: Modifier = Modifier
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
                    text = "가장 최근 후기",
                    color = Grey600,
                    style = MaterialTheme.typography.caption200.copy(
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier
                        .wrapContentHeight(align = Alignment.CenterVertically)
                )
                Text(
                    text = "2023년 4월",
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
                        text = "5.0",
                        color = Grey600,
                        style = MaterialTheme.typography.caption200.copy(

                        ),
                        modifier = Modifier
                            .wrapContentHeight(align = Alignment.CenterVertically)
                    )
                    Badge(colorType = BadgeColorType.ORANGE, text = "육아")
                    Badge(colorType = BadgeColorType.ORANGE, text = "가사")
                }
            }
            Text(
                text = "어머님께서 인품이 선하시고 너무 친절하십니다~ ㅎㅎ \n아이들도 정말 귀엽고 순해요! 다음에도 또 뵈면 좋겠네요~",
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
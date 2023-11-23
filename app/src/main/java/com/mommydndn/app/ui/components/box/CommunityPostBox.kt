package com.mommydndn.app.ui.components.box

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.mommydndn.app.R
import com.mommydndn.app.data.model.common.CommunityPost
import com.mommydndn.app.ui.theme.Grey300
import com.mommydndn.app.ui.theme.Grey400
import com.mommydndn.app.ui.theme.Grey600
import com.mommydndn.app.ui.theme.Grey700
import com.mommydndn.app.ui.theme.Salmon600
import com.mommydndn.app.ui.theme.White
import com.mommydndn.app.ui.theme.caption100
import com.mommydndn.app.ui.theme.caption200
import com.mommydndn.app.ui.theme.paragraph300

@Composable
fun CommunityPostBox(
    modifier: Modifier = Modifier,
    item: CommunityPost
) {

    val profilePainter = rememberImagePainter(
        data = item.userProfileImgUrl,
        builder = {
            crossfade(true)
        }
    )

    Box(
        modifier = modifier
            .width(326.dp)
            .background(color = White)
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Row(modifier = Modifier.fillMaxWidth()) {
                Image(
                    painter = profilePainter,
                    contentDescription = null,
                    modifier = Modifier
                        .size(36.dp)
                        .clip(CircleShape)
                )
                Spacer(modifier = Modifier.padding(6.dp))
                Column() {
                    Text(
                        text = item.userName,
                        style = MaterialTheme.typography.caption200.copy(
                            fontWeight = FontWeight.Bold,
                            color = Grey600
                        )
                    )
                    Spacer(modifier = Modifier.padding(2.dp))
                    Text(
                        text = item.date,
                        style = MaterialTheme.typography.caption100.copy(
                            fontWeight = FontWeight.Normal,
                            color = Grey400
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.padding(8.dp))

            Text(
                text = item.title,
                style = MaterialTheme.typography.paragraph300.copy(
                    fontWeight = FontWeight.Bold,
                    color = Grey700
                )
            )

            Spacer(modifier = Modifier.padding(4.dp))

            Text(
                text = item.content,
                style = MaterialTheme.typography.caption200.copy(
                    fontWeight = FontWeight.Medium,
                    color = Grey600
                )

            )
            Spacer(modifier = Modifier.padding(12.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                IconButtonWithNumber(
                    iconResource = painterResource(id = R.drawable.ic_chatbubble),
                    number = 1
                )
                Spacer(modifier = Modifier.padding(12.dp))
                LikeButtonWithNumber(status = true, onClick = {})
            }
        }
    }
}

@Composable
fun LikeButtonWithNumber(
    status: Boolean = false,
    onClick: ((Boolean) -> Unit)? = null
) {
    IconButtonWithNumber(
        iconResource = painterResource(id = R.drawable.ic_heart_fill),
        number = 1,
        iconColor = if (status) Salmon600 else Grey300,
        textColor = if (status) Salmon600 else Grey300,
        onClick = {
            onClick?.let { it(!status) }
        }
    )
}

@Composable
fun IconButtonWithNumber(
    iconResource: Painter,
    number: Int,
    iconColor: Color = Grey300,
    textColor: Color = Grey300,
    onClick: (() -> Unit)? = null
) {
    Row(
        modifier = Modifier.clickable { onClick?.let { it() } },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = iconResource,
            contentDescription = null,
            modifier = Modifier
                .size(24.dp),
            colorFilter = ColorFilter.tint(iconColor),
        )
        Spacer(modifier = Modifier.padding(4.dp))
        Text(
            text = number.toString(),
            style = MaterialTheme.typography.caption200.copy(
                fontWeight = FontWeight.Medium,
                color = textColor
            )
        )
    }

}


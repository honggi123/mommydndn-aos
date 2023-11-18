package com.mommydndn.app.ui.components.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
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
import coil.compose.rememberImagePainter
import com.mommydndn.app.R
import com.mommydndn.app.data.model.common.BadgeColorType
import com.mommydndn.app.ui.components.box.ProfileDataBox
import com.mommydndn.app.ui.theme.Grey300
import com.mommydndn.app.ui.theme.Grey600
import com.mommydndn.app.ui.theme.White
import com.mommydndn.app.ui.theme.caption100
import com.mommydndn.app.ui.theme.paragraph400

@Composable
fun SitterListItem(
    modifier: Modifier = Modifier,
) {

    val profilePainter = rememberImagePainter(
        data = "",
        builder = {
            crossfade(true)
        }
    )

    Column(
        verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.Top),
        modifier = modifier
            .requiredWidth(width = 334.dp)
            .clip(shape = RoundedCornerShape(6.dp))
            .background(color = White)
            .padding(all = 6.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .requiredWidth(147.dp)
                    .requiredHeight(height = 72.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Box(
                    modifier = Modifier
                        .requiredSize(size = 72.dp)
                        .background(color = White)
                ) {
                    Image(
                        painter = profilePainter,
                        contentDescription = null,
                        modifier = Modifier
                            .size(72.dp)
                            .clip(CircleShape)
                            .background(Grey300)
                    )
                    Icon(
                        painter = painterResource(id = R.drawable.ic_certificate),
                        contentDescription = "Icon/certificate",
                        modifier = Modifier
                            .align(alignment = Alignment.BottomEnd)
                            .clip(shape = RoundedCornerShape(34.dp))
                    )
                }
                Column(
                    verticalArrangement = Arrangement.spacedBy(4.dp, Alignment.CenterVertically),
                ) {
                    Text(
                        text = "세아쌤",
                        color = Grey600,
                        style = MaterialTheme.typography.paragraph400.copy(
                            fontWeight = FontWeight.Bold
                        )
                    )
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(2.dp, Alignment.Start),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_marker),
                            contentDescription = "",
                            modifier = Modifier
                                .requiredSize(size = 16.dp)
                        )
                        Text(
                            text = "반포동",
                            color = Grey600,
                            style = MaterialTheme.typography.caption100.copy(
                                fontWeight = FontWeight.Medium
                            ),
                            modifier = Modifier
                                .wrapContentHeight(align = Alignment.CenterVertically)
                        )
                    }
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(4.dp, Alignment.Start),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_muscle),
                            contentDescription = "Icon/muscle",
                            modifier = Modifier
                                .requiredSize(size = 16.dp)
                        )
                        Text(
                            text = "5.0",
                            color = Grey600,
                            style = MaterialTheme.typography.caption100.copy(
                                fontWeight = FontWeight.Medium
                            ),
                            modifier = Modifier
                                .wrapContentHeight(align = Alignment.CenterVertically)
                        )
                    }
                }
            }
            Image(
                painter = painterResource(id = R.drawable.ic_heart_grey),
                contentDescription = "",
                modifier = Modifier
                    .requiredSize(size = 36.dp)
            )
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(3.dp, Alignment.Start)
        ) {
            Badge(colorType = BadgeColorType.ORANGE)
        }
        ProfileDataBox()
    }
}


@Preview(widthDp = 334, heightDp = 171)
@Composable
private fun SitterListItemPreview() {
    SitterListItem(Modifier)
}
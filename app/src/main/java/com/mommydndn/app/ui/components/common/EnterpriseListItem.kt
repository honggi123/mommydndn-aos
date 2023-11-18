package com.mommydndn.app.ui.components.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.mommydndn.app.R
import com.mommydndn.app.data.model.common.BadgeColorType
import com.mommydndn.app.ui.components.box.ProfileDataBox
import com.mommydndn.app.ui.theme.Grey300
import com.mommydndn.app.ui.theme.Grey600
import com.mommydndn.app.ui.theme.White
import com.mommydndn.app.ui.theme.caption100
import com.mommydndn.app.ui.theme.caption200
import com.mommydndn.app.ui.theme.paragraph300
import com.mommydndn.app.ui.theme.paragraph400

@Composable
fun EnterpriseListItem(
    modifier: Modifier = Modifier
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
            .clip(shape = RoundedCornerShape(6.dp))
            .background(color = White)
            .padding(all = 6.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp, Alignment.CenterVertically)
            ) {
                Row {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_certificate),
                        contentDescription = "Icon/certificate",
                        modifier = Modifier.size(24.dp)
                    )
                    Badge(colorType = BadgeColorType.BLUE, text = "안심업체 인증")
                }

                Text(
                    text = "피카부 베이비시터",
                    color = Grey600,
                    style = MaterialTheme.typography.paragraph300.copy(
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
                        style = MaterialTheme.typography.caption200.copy(
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
                    Image(
                        painter = painterResource(id = R.drawable.ic_muscle),
                        contentDescription = "Icon/muscle",
                        modifier = Modifier
                            .requiredSize(size = 16.dp)
                    )
                    Text(
                        text = "5.0",
                        color = Grey600,
                        style = MaterialTheme.typography.caption200.copy(
                            fontWeight = FontWeight.Medium
                        )
                    )
                }
                Row(
                    horizontalArrangement = Arrangement.spacedBy(3.dp, Alignment.Start)
                ) {
                    Badge(colorType = BadgeColorType.ORANGE)
                }
            }

            Box(
                modifier = Modifier
                    .requiredSize(size = 72.dp)
            ) {
                Image(
                    painter = profilePainter,
                    contentDescription = null,
                    modifier = Modifier
                        .width(130.dp)
                        .height(124.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(Grey300)
                )
                Image(
                    painter = painterResource(id = R.drawable.ic_heart_grey),
                    contentDescription = "",
                    modifier = Modifier
                        .requiredSize(size = 36.dp)
                        .align(alignment = Alignment.TopEnd)
                )
            }
        }

        ProfileDataBox(modifier = Modifier.fillMaxWidth())
    }
}
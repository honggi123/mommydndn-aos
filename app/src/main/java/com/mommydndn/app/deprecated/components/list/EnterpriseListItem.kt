package com.mommydndn.app.deprecated.components.list

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.mommydndn.app.R
import com.mommydndn.app.data.model.care.CaringType
import com.mommydndn.app.deprecated.components.box.ProfileDataBox
import com.mommydndn.app.deprecated.models.care.ProfileBoxType
import com.mommydndn.app.ui.theme.Grey300
import com.mommydndn.app.ui.theme.Grey600
import com.mommydndn.app.ui.theme.White
import com.mommydndn.app.ui.theme.caption200
import com.mommydndn.app.ui.theme.paragraph300

@Composable
fun EnterpriseListItem(
    modifier: Modifier = Modifier,
    nickname: String,
    neighborhood: String,
    profileUrl: String? = "",
    isDndnAuthenticated: Boolean,
    dndnScore: Double? = 0.0,
    caringTypeCodeList: List<CaringType>? = emptyList(),
    matchingCount: Int,
    reviewCount: Int,
    responseRate: String,
    isLiked: Boolean? = false,
    profileBoxType: ProfileBoxType = ProfileBoxType.FEED
) {

    val profilePainter = rememberImagePainter(
        data = profileUrl,
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
                    Image(
                        painter = painterResource(id = R.drawable.icon_certificate),
                        contentDescription = "Icon/certificate",
                        modifier = Modifier.size(24.dp),
                        contentScale = ContentScale.Crop
                    )
                    // TODO
                    // TagLabel(colorType = BadgeColorType.BLUE, text = "안심업체 인증")
                }

                Text(
                    text = nickname,
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
                        text = neighborhood,
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
                        text = dndnScore.toString(),
                        color = Grey600,
                        style = MaterialTheme.typography.caption200.copy(
                            fontWeight = FontWeight.Medium
                        )
                    )
                }

                if (profileBoxType == ProfileBoxType.FEED) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(3.dp, Alignment.Start)
                    ) {
                        caringTypeCodeList?.forEach {
                            // TODO
                            // TagLabel(colorType = BadgeColorType.ORANGE, text = it.value)
                        }
                    }
                }
            }
            if (profileBoxType == ProfileBoxType.FEED) {
                Box(
                    modifier = Modifier
                        .width(130.dp)
                        .height(124.dp)
                ) {
                    Image(
                        painter = profilePainter,
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(RoundedCornerShape(8.dp))
                            .background(Grey300),
                        contentScale = ContentScale.Crop
                    )
                    Image(
                        painter = painterResource(id = R.drawable.icon_heart),
                        contentDescription = "",
                        modifier = Modifier
                            .requiredSize(size = 36.dp)
                            .align(alignment = Alignment.TopEnd)
                    )
                }
            }
        }

        ProfileDataBox(
            modifier = Modifier.fillMaxWidth(),
            matchCount = matchingCount,
            reviewCount = reviewCount,
            responseRate = responseRate
        )
    }
}
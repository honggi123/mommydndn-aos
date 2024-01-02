package com.mommydndn.app.ui.components.list

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
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
import com.mommydndn.app.ui.components.box.ProfileDataBox
import com.mommydndn.app.ui.models.care.ProfileBoxType
import com.mommydndn.app.ui.theme.Grey300
import com.mommydndn.app.ui.theme.Grey600
import com.mommydndn.app.ui.theme.White
import com.mommydndn.app.ui.theme.caption100
import com.mommydndn.app.ui.theme.paragraph300

@Composable
fun SitterListItem(
    modifier: Modifier = Modifier,
    profileUrl: String = "",
    isDndnAuthenticated: Boolean,
    nickname: String,
    neighborhood: String,
    responseRate: String,
    ageAndGender: String = "",
    caringTypeCodeList: List<CaringType> = emptyList(),
    matchingCount:Int,
    reviewCount: Int,
    profileType: ProfileBoxType = ProfileBoxType.FEED
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
            Row(horizontalArrangement = Arrangement.SpaceBetween) {
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
                            .background(Grey300),
                        contentScale = ContentScale.Crop
                    )
                    if (isDndnAuthenticated) {
                        Image(
                            painter = painterResource(id = R.drawable.icon_certificate),
                            contentDescription = "Icon/certificate",
                            modifier = Modifier
                                .align(alignment = Alignment.BottomEnd)
                                .clip(shape = RoundedCornerShape(34.dp))
                        )
                    }
                }

                Spacer(modifier = Modifier.width(21.dp))

                Column(
                    verticalArrangement = Arrangement.spacedBy(4.dp, Alignment.CenterVertically),
                ) {
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
                            text = responseRate,
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
                painter = painterResource(id = R.drawable.icon_heart),
                contentDescription = "",
                modifier = Modifier
                    .requiredSize(size = 36.dp)
            )
        }

        if (profileType == ProfileBoxType.FEED) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(3.dp, Alignment.Start)
            ) {
                // TODO
                // TagLabel(colorType = BadgeColorType.GREEN, text = ageAndGender)

                caringTypeCodeList.forEach {
                    // TODO
                    // TagLabel(colorType = BadgeColorType.ORANGE, text = it.value)
                }
            }
        }
        ProfileDataBox(
            matchCount = matchingCount,
            reviewCount = reviewCount,
            responseRate = responseRate
        )
    }
}


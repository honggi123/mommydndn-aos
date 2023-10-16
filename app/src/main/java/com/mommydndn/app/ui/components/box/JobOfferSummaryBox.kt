package com.mommydndn.app.ui.components.box

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.material.Badge
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.mommydndn.app.R
import com.mommydndn.app.data.model.BannerColorType
import com.mommydndn.app.data.model.JobOfferSummary
import com.mommydndn.app.ui.theme.Grey400
import com.mommydndn.app.ui.theme.Grey600
import com.mommydndn.app.ui.theme.Grey700
import com.mommydndn.app.ui.theme.White
import com.mommydndn.app.ui.theme.caption100
import com.mommydndn.app.ui.theme.caption200
import com.mommydndn.app.ui.theme.paragraph300
import com.mommydndn.app.ui.components.common.Badge
import com.mommydndn.app.ui.theme.Grey500
import com.mommydndn.app.ui.theme.Grey800

@Composable
fun JobOfferSummaryBox(
    item: JobOfferSummary
) {
    Box(
        modifier = Modifier
            .width(334.dp)
            .background(color = White)
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Row {
                        item.caringTypeCodeList.forEach {
                            if (it.equals("마감")) Badge(colorType = BannerColorType.ORANGE, text = it)
                            else Badge(colorType = BannerColorType.ORANGE, text = it)
                        }
                    }

                    Text(
                        text = item.title,
                        style = MaterialTheme.typography.caption200.copy(
                            fontWeight = FontWeight.Medium,
                            color = Grey700
                        )
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = item.neighborhood,
                            style = MaterialTheme.typography.caption100.copy(
                                fontWeight = FontWeight.Normal,
                                color = Grey500
                            )
                        )
                        Spacer(modifier = Modifier.size(4.dp))
                        Image(
                            painter = painterResource(id = R.drawable.ic_ellipse),
                            contentDescription = null,
                            modifier = Modifier.align(Alignment.CenterVertically)
                        )
                        Spacer(modifier = Modifier.size(4.dp))

                        Text(
                            text = item.createdAt,
                            style = MaterialTheme.typography.caption100.copy(
                                fontWeight = FontWeight.Normal,
                                color = Grey500
                            )
                        )
                    }
                }

                Image(
                    painter = painterResource(id = R.drawable.ic_heart_fill),
                    contentDescription = null,
                    modifier = Modifier
                        .size(36.dp)
                        .clip(CircleShape)
                )

            }

            Spacer(modifier = Modifier.padding(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom
            ) {
                Row (
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Text(
                        text = "토,일",
                        style = MaterialTheme.typography.caption100.copy(
                            fontWeight = FontWeight.Normal,
                            color = Grey500
                        )
                    )
                    Spacer(modifier = Modifier.size(4.dp))
                    Image(
                        painter = painterResource(id = R.drawable.ic_ellipse),
                        contentDescription = null,
                        modifier = Modifier.align(Alignment.CenterVertically)
                    )
                    Spacer(modifier = Modifier.size(4.dp))
                    Text(
                        text = item.startTime + "~" + item.endTime,
                        style = MaterialTheme.typography.caption100.copy(
                            fontWeight = FontWeight.Normal,
                            color = Grey500
                        )
                    )
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = item.salaryTypeCode,
                        style = MaterialTheme.typography.paragraph300.copy(
                            fontWeight = FontWeight.Bold,
                            color = Grey800
                        )
                    )
                    Text(
                        text = item.salary.toString(),
                        style = MaterialTheme.typography.paragraph300.copy(
                            fontWeight = FontWeight.Bold,
                            color = Grey800
                        )
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun JobOfferSummaryBoxPreview() {
    val jobOffer = JobOfferSummary(
        caringTypeCodeList = listOf("육아", "가사"),
        createdAt = "2023-10-10",
        dayTypeCodeList = listOf("토", "일"),
        endDate = 31,
        endTime = "18:00",
        isLiked = false,
        isMatched = true,
        jobOfferId = 1,
        neighborhood = "서울",
        salary = 3000,
        salaryTypeCode = "시급",
        startDate = 1,
        startTime = "09:00",
        title = "일자리 제목"
    )

    JobOfferSummaryBox(item = jobOffer)
}

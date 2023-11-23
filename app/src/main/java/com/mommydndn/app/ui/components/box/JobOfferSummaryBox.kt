package com.mommydndn.app.ui.components.box

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.mommydndn.app.R
import com.mommydndn.app.data.model.care.summary.JobOfferSummaryListItem
import com.mommydndn.app.data.model.common.BadgeColorType
import com.mommydndn.app.ui.theme.Grey700
import com.mommydndn.app.ui.theme.White
import com.mommydndn.app.ui.theme.caption100
import com.mommydndn.app.ui.theme.caption200
import com.mommydndn.app.ui.theme.paragraph300
import com.mommydndn.app.ui.components.common.Badge
import com.mommydndn.app.ui.theme.Grey500
import com.mommydndn.app.ui.theme.Grey800
import com.mommydndn.app.util.DateTimeUtils
import com.mommydndn.app.util.NumberUtils

@Composable
fun JobOfferSummaryBox(
    modifier: Modifier = Modifier,
    item: JobOfferSummaryListItem
) {
    Box(
        modifier = modifier
            .width(334.dp)
            .background(color = White)
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(2.dp)
                ) {
                    Row {
                        item.caringTypeCodeList.forEach {
                            if (it.equals("마감")) Badge(colorType = BadgeColorType.ORANGE, text = it.value)
                            else Badge(colorType = BadgeColorType.ORANGE, text = it.value)
                            Spacer(modifier = Modifier.width(3.dp))
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
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Text(
                            text = item.neighborhood,
                            style = MaterialTheme.typography.caption100.copy(
                                fontWeight = FontWeight.Normal,
                                color = Grey500
                            )
                        )
                        Image(
                            painter = painterResource(id = R.drawable.ic_ellipse),
                            contentDescription = null,
                            modifier = Modifier.align(Alignment.CenterVertically)
                        )

                        Text(
                            text = DateTimeUtils.formatTimestampToMonthDay(item.createdAt),
                            style = MaterialTheme.typography.caption100.copy(
                                fontWeight = FontWeight.Normal,
                                color = Grey500
                            )
                        )
                    }
                }

                Image(
                    painter = painterResource(id = R.drawable.ic_heart_grey),
                    contentDescription = null,
                    modifier = Modifier
                        .size(36.dp)
                )

            }

            Spacer(modifier = Modifier.height(12.dp))

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
                        text = "${item.salaryTypeCode.value} ${NumberUtils.getPriceString(item.salary)}",
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

package com.mommydndn.app.ui.care.list.job

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mommydndn.app.R
import com.mommydndn.app.domain.model.CareType
import com.mommydndn.app.domain.model.PayPeriod
import com.mommydndn.app.domain.model.WorkPeriod
import com.mommydndn.app.ui.care.list.components.CareTypeTag
import com.mommydndn.app.ui.care.list.components.ClosedCareJobTag
import com.mommydndn.app.ui.care.list.components.WorkPeriodTag
import com.mommydndn.app.ui.care.list.components.asTimeAgo
import com.mommydndn.app.ui.care.list.components.displayName
import com.mommydndn.app.ui.care.list.jobs
import com.mommydndn.app.ui.theme.Grey50
import com.mommydndn.app.ui.theme.Grey500
import com.mommydndn.app.ui.theme.Grey700
import com.mommydndn.app.ui.theme.Grey800
import com.mommydndn.app.ui.theme.Salmon600
import com.mommydndn.app.ui.theme.caption100
import com.mommydndn.app.ui.theme.paragraph300
import com.mommydndn.app.ui.theme.paragraph400
import java.text.DecimalFormat
import java.time.DayOfWeek
import java.time.LocalTime
import java.time.ZonedDateTime
import java.time.format.TextStyle
import java.util.Locale

data class CareJobUiModel(
    val workPeriod: WorkPeriod,
    val careTypes: Set<CareType>,
    val isClosed: Boolean,
    val title: String,
    val isLiked: Boolean,
    val neighborhoodName: String,
    val createdAt: ZonedDateTime,
    val daysOfWeek: List<DayOfWeek>,
    val startTime: LocalTime,
    val endTime: LocalTime,
    val payPeriod: PayPeriod,
    val pay: Int,
)

@Composable
fun CareJobList(
    jobs: List<CareJobUiModel>,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(horizontal = 28.dp, vertical = 16.dp),
    ) {
        itemsIndexed(jobs) { index, job ->
            with(job) {
                CareJobListItem(
                    workPeriod = workPeriod,
                    careTypes = careTypes,
                    isClosed = isClosed,
                    title = title,
                    isLiked = isLiked,
                    neighborhoodName = neighborhoodName,
                    createdAt = createdAt,
                    daysOfWeek = daysOfWeek,
                    startTime = startTime,
                    endTime = endTime,
                    payPeriod = payPeriod,
                    pay = pay,
                )
            }

            if (index != jobs.lastIndex) {
                Divider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 20.dp),
                    color = Grey50,
                    thickness = 1.5.dp,
                )
            }
        }
    }
}

@Composable
fun CareJobListItem(
    workPeriod: WorkPeriod,
    careTypes: Set<CareType>,
    isClosed: Boolean,
    title: String,
    isLiked: Boolean,
    neighborhoodName: String,
    createdAt: ZonedDateTime,
    daysOfWeek: List<DayOfWeek>,
    startTime: LocalTime,
    endTime: LocalTime,
    payPeriod: PayPeriod,
    pay: Int,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            Column(
                modifier = Modifier.weight(1F),
                verticalArrangement = Arrangement.spacedBy(2.dp),
                horizontalAlignment = Alignment.Start,
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    WorkPeriodTag(
                        workPeriod = workPeriod,
                        modifier = Modifier
                    )

                    if (isClosed) {
                        ClosedCareJobTag(modifier = Modifier)
                    } else {
                        // TODO: 순서
                        careTypes.forEach { careType ->
                            CareTypeTag(
                                careType = careType,
                                modifier = Modifier,
                            )
                        }
                    }
                }

                Text(
                    text = title,
                    color = Grey700,
                    style = MaterialTheme.typography.paragraph300.merge(
                        fontWeight = FontWeight.Medium,
                    ),
                )

                NeighborhoodNameAndCreationTime(
                    neighborhoodName = neighborhoodName,
                    createdAt = createdAt,
                    modifier = Modifier,
                )
            }

            Icon(
                painter = painterResource(
                    id = if (isLiked) {
                        R.drawable.icon_heart_fill
                    } else {
                        R.drawable.icon_heart
                    },
                ),
                contentDescription = "CareJobOpeningListItem_Heart",
                modifier = Modifier.size(36.dp),
                tint = if (isLiked) {
                    Salmon600
                } else {
                    Color.Unspecified
                }
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Bottom,
        ) {
            WorkDaysOfWeekAndHours(
                daysOfWeek = daysOfWeek,
                startTime = startTime,
                endTime = endTime,
                modifier = Modifier,
            )

            Text(
                text = "${payPeriod.displayName()} ${payFormatter.format(pay)}",
                style = MaterialTheme.typography.paragraph400.merge(
                    color = Grey800,
                    fontWeight = FontWeight.Bold,
                ),
            )
        }
    }
}

private val payFormatter = DecimalFormat("#,###")

@Composable
private fun NeighborhoodNameAndCreationTime(
    neighborhoodName: String,
    createdAt: ZonedDateTime,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        val textStyle = MaterialTheme.typography.caption100.merge(
            color = Grey500,
            fontWeight = FontWeight.Normal,
        )

        Text(
            text = neighborhoodName,
            style = textStyle,
        )

        Text(
            text = stringResource(R.string.interpunct),
            style = textStyle,
        )

        Text(
            text = createdAt.asTimeAgo(),
            style = textStyle,
        )
    }
}

@Composable
private fun WorkDaysOfWeekAndHours(
    daysOfWeek: List<DayOfWeek>,
    startTime: LocalTime,
    endTime: LocalTime,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalAlignment = Alignment.Bottom,
    ) {
        val textStyle = MaterialTheme.typography.paragraph300.merge(
            color = Grey500,
            fontWeight = FontWeight.Medium,
        )

        Text(
            text = daysOfWeek.displayName(),
            style = textStyle,
        )

        Text(
            text = stringResource(R.string.interpunct),
            style = textStyle,
        )

        Text(
            text = "$startTime ~ $endTime",
            style = textStyle,
        )
    }
}

@Composable
private fun List<DayOfWeek>.displayName(): String {
    return take(3)
        .joinToString(separator = "") { it.getDisplayName(TextStyle.NARROW, Locale.KOREAN) }
        .run {
            if (size > 3) {
                this + stringResource(R.string.other_size, size - 3)
            } else {
                this
            }
        }
}

@Preview
@Composable
private fun CareJobListItemPreview() {
    CareJobListItem(
        workPeriod = WorkPeriod.Regular,
        careTypes = setOf(
            CareType.ChildCare,
            CareType.Housekeeping,
        ),
        isClosed = false,
        title = "2일간 풀타임으로 아이 둘 맡아주실 분 구해요",
        isLiked = true,
        neighborhoodName = "반포동",
        createdAt = ZonedDateTime.now()
            .minusHours(2)
            .minusMinutes(19),
        daysOfWeek = listOf(
            DayOfWeek.MONDAY,
            DayOfWeek.WEDNESDAY,
            DayOfWeek.FRIDAY,
        ),
        startTime = LocalTime.of(17, 0),
        endTime = LocalTime.of(22, 0),
        payPeriod = PayPeriod.Hourly,
        pay = 12000,
        modifier = Modifier
            .background(Color.White)
            .padding(horizontal = 28.dp, vertical = 16.dp)
    )
}

@Preview
@Composable
private fun CareJobListPreview() {
    CareJobList(
        jobs = jobs,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
    )
}
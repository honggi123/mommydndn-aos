package com.mommydndn.app.feature.care.jobopening

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
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
import com.mommydndn.app.domain.model.care.CareType
import com.mommydndn.app.domain.model.care.PayPeriod
import com.mommydndn.app.domain.model.care.WorkPeriod
import com.mommydndn.app.feature.care.screen.displayName
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
import java.time.Duration
import java.time.LocalTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.TextStyle
import java.util.Locale

@Composable
internal fun CareJobOpeningListFragment(
    items: List<CareJobOpeningListItem>,
    modifier: Modifier = Modifier,
) {
    // todo: empty, background?
    LazyColumn(modifier = modifier.background(Color.White)) {
        itemsIndexed(items) { index, jobOpening ->
            with(jobOpening) {
                CareJobOpeningListItem(
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
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(horizontal = 28.dp, vertical = 20.dp),
                )

                if (index != items.lastIndex) {
                    CareJobOpeningListDivider(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 28.dp),
                    )
                }
            }
        }
    }
}

@Composable
internal fun CareJobOpeningListItem(
    workPeriod: WorkPeriod,
    careTypes: List<CareType>,
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
                CareJobOpeningTags(
                    workPeriod = workPeriod,
                    careTypes = careTypes,
                    isClosed = isClosed,
                    modifier = Modifier,
                )

                Text(
                    text = title,
                    style = MaterialTheme.typography.paragraph300.merge(
                        color = Grey700,
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
private fun CareJobOpeningTags(
    workPeriod: WorkPeriod,
    careTypes: List<CareType>,
    isClosed: Boolean,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(3.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        CareJobOpeningWorkPeriod(
            workPeriod = workPeriod,
            modifier = Modifier
        )

        if (isClosed) {
            ClosedCareJobOpening(modifier = Modifier)
        } else {
            careTypes.sorted().forEach { careType ->
                CareJobOpeningCareType(
                    careType = careType,
                    modifier = Modifier,
                )
            }
        }
    }
}

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
private fun ZonedDateTime.asTimeAgo(): String {
    val now = ZonedDateTime.now(ZoneId.of("Asia/Seoul"))

    val duration = Duration.between(this, now)

    val days = duration.toDays()

    val hours = duration.toHours()

    return if (days > 0) {
        stringResource(R.string.days_ago, days)
    } else if (hours > 0) {
        stringResource(R.string.hours_ago, hours)
    } else {
        stringResource(R.string.minutes_ago, duration.toMinutes())
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

        // todo
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
    return take(2)
        .joinToString { it.getDisplayName(TextStyle.NARROW, Locale.KOREAN) }
        .run {
            if (size >= 3) {
                this + stringResource(R.string.other_size, size - 2)
            } else {
                this
            }
        }
}

@Composable
private fun CareJobOpeningListDivider(modifier: Modifier = Modifier) {
    Divider(
        modifier = modifier.background(Color.Transparent),
        color = Grey50,
        thickness = 1.5.dp,
    )
}

@Preview
@Composable
private fun PreviewCareJobOpeningListItem() {
    CareJobOpeningListItem(
        workPeriod = WorkPeriod.REGULAR,
        careTypes = listOf(
            CareType.CHILD_CARE,
            CareType.HOUSEKEEPING,
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
        payPeriod = PayPeriod.HOURLY,
        pay = 12000,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(Color.White)
            .padding(horizontal = 28.dp, vertical = 20.dp)
    )
}

@Preview
@Composable
private fun PreviewCareJobOpeningListFragment() {
    CareJobOpeningListFragment(
        items = mockCareJobOpeningListItems,
        modifier = Modifier.fillMaxSize().background(Color.White),
    )
}
package com.mommydndn.app.feature.care.filters.modal

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mommydndn.app.R
import com.mommydndn.app.feature.care.filters.WorkHoursFilter
import com.mommydndn.app.ui.theme.Grey400
import com.mommydndn.app.ui.theme.Grey50
import com.mommydndn.app.ui.theme.Grey800
import com.mommydndn.app.ui.theme.AppTheme
import com.mommydndn.app.ui.theme.heading700
import com.mommydndn.app.ui.theme.paragraph300
import java.time.LocalTime
import java.time.format.DateTimeFormatter

// todo
private val timeFormatter = DateTimeFormatter.ofPattern("HH:mm")

@Composable
internal fun WorkHoursFilterModalBottomSheet(
    workHoursFilter: WorkHoursFilter,
    onRefreshClick: () -> Unit,
    onCloseClick: () -> Unit,
    onUpdateClick: (WorkHoursFilter) -> Unit,
    modifier: Modifier = Modifier,
) {
    val filter = remember {
        mutableStateOf(workHoursFilter)
    }

    val startTime = remember {
        mutableStateOf(workHoursFilter.startTime)
    }

    val endTime = remember {
        mutableStateOf(workHoursFilter.endTime)
    }

    CareFilterModalBottomSheet(
        onCloseClick = onCloseClick,
        onUpdateClick = {
            onUpdateClick(
                workHoursFilter.copy(
                    startTime = startTime.value,
                    endTime = endTime.value,
                )
            )
        },
        modifier = modifier,
    ) {
        ModalBottomSheetTitleWithRefreshButton(
            title = stringResource(id = R.string.time),
            // todo: refresh policy
            onRefreshClick = onRefreshClick,
            modifier = Modifier,
        )

        ModalBottomSheetHorizontalDivider(
            modifier = Modifier.padding(vertical = 12.dp)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            startTime.value.let { startTime ->
                WorkHour(
                    time = startTime,
                    text = if (startTime == null) {
                        stringResource(R.string.start_time)
                    } else {
                        startTime.format(timeFormatter)
                    },
                    onClick = {},
                    modifier = Modifier.weight(1F),
                )
            }

            Text(
                text = stringResource(R.string.tilde),
                style = MaterialTheme.typography.heading700.merge(
                    color = Grey400,
                    fontWeight = FontWeight.Normal,
                ),
                modifier = Modifier.padding(horizontal = 4.dp),
            )

            endTime.value.let { endTime ->
                WorkHour(
                    time = endTime,
                    text = if (endTime == null) {
                        stringResource(R.string.end_time)
                    } else {
                        endTime.format(timeFormatter)
                    },
                    onClick = {},
                    modifier = Modifier.weight(1F),
                )

            }
        }
    }
}

// todo: update time
@Composable
private fun WorkHour(
    time: LocalTime?,
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .height(height = 56.dp)
            .clip(shape = RoundedCornerShape(12.dp))
            .background(color = Grey50)
            /*
            .border( // todo: border?
                1.dp,
                if (time != null) {
                    Salmon600
                } else {
                    Color.Transparent
                },
                RoundedCornerShape(12.dp)
            )
             */
            .padding(start = 20.dp, end = 8.dp)
            .clickable(onClick = onClick)

    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.paragraph300.copy(
                color = if (time != null) {
                    Grey800
                } else {
                    Grey400
                },
                fontWeight = FontWeight.Normal,
            ),
        )

        Image(
            painter = painterResource(id = R.drawable.icon_arrow_down),
            contentDescription = "WorkHour_ArrowDown",
            modifier = Modifier.size(size = 28.dp)
        )
    }
}

@Preview
@Composable
private fun PreviewWorkHoursFilterModalBottomSheet() {
    AppTheme {
        WorkHoursFilterModalBottomSheet(
            workHoursFilter = WorkHoursFilter(
                startTime = null,
                endTime = null
            ),
            onRefreshClick = {},
            onCloseClick = {},
            onUpdateClick = {},
            modifier = Modifier,
        )
    }
}

@Preview
@Composable
private fun PreviewWorkHoursFilterModalBottomSheet_WithTimes() {
    AppTheme {
        WorkHoursFilterModalBottomSheet(
            workHoursFilter = WorkHoursFilter(
                startTime = LocalTime.of(11, 0),
                endTime = LocalTime.of(14, 0)
            ),
            onRefreshClick = {},
            onCloseClick = {},
            onUpdateClick = {},
            modifier = Modifier,
        )
    }
}
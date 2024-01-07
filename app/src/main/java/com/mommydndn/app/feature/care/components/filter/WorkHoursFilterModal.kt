package com.mommydndn.app.feature.care.components.filter

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mommydndn.app.R
import com.mommydndn.app.ui.theme.Grey400
import com.mommydndn.app.ui.theme.Grey50
import com.mommydndn.app.ui.theme.Grey600
import com.mommydndn.app.ui.theme.Grey800
import com.mommydndn.app.ui.theme.MommydndnTheme
import com.mommydndn.app.ui.theme.heading700
import com.mommydndn.app.ui.theme.paragraph300
import java.time.LocalTime
import java.time.format.DateTimeFormatter

// todo
private val timeFormatter = DateTimeFormatter.ofPattern("HH:mm")

data class WorkHoursUiModel(
    val startTime: LocalTime?,
    val endTime: LocalTime?
)

@Composable
internal fun WorkHoursFilterModal(
    workHours: WorkHoursUiModel,
    onResetClick: () -> Unit,
    onCloseClick: () -> Unit,
    onApplyClick: (WorkHoursUiModel) -> Unit,
    modifier: Modifier = Modifier,
) {
    CareFilterModal(
        onCloseClick = onCloseClick,
        onApplyClick = {
            onApplyClick(workHours)
        },
        modifier = modifier,
    ) {
        // TODO
        ModalTitleWithRefreshButton(
            title = stringResource(id = R.string.time),
            onRefreshClick = onResetClick,
            modifier = Modifier,
        )

        ModalHorizontalDivider(Modifier.padding(vertical = 12.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            workHours.startTime.let { startTime ->
                WorkHour(
                    time = startTime,
                    text = if (startTime == null) {
                        stringResource(R.string.start_time)
                    } else {
                        startTime.format(timeFormatter)
                    },
                    // TODO
                    onClick = {},
                    modifier = Modifier.weight(1F),
                )
            }

            Text(
                text = stringResource(R.string.tilde),
                modifier = Modifier.padding(horizontal = 4.dp),
                color = Grey400,
                style = MaterialTheme.typography.heading700.merge(
                    fontWeight = FontWeight.Normal,
                ),
            )

            workHours.endTime.let { endTime ->
                WorkHour(
                    time = endTime,
                    text = if (endTime == null) {
                        stringResource(R.string.end_time)
                    } else {
                        endTime.format(timeFormatter)
                    },
                    // TODO
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
            color = if (time != null) {
                Grey800
            } else {
                Grey400
            },
            style = MaterialTheme.typography.paragraph300.merge(
                fontWeight = FontWeight.Normal,
            ),
        )

        Icon(
            painter = painterResource(id = R.drawable.icon_arrow_down),
            contentDescription = "WorkHour_ArrowDown",
            modifier = Modifier.size(size = 28.dp),
            tint = Grey600,
        )
    }
}

@Preview
@Composable
private fun PreviewWorkHoursFilterModalBottomSheet() {
    MommydndnTheme {
        WorkHoursFilterModal(
            workHours = WorkHoursUiModel(
                startTime = null,
                endTime = null
            ),
            onResetClick = {},
            onCloseClick = {},
            onApplyClick = {},
            modifier = Modifier,
        )
    }
}

@Preview
@Composable
private fun PreviewWorkHoursFilterModalBottomSheet_WithTimes() {
    MommydndnTheme {
        WorkHoursFilterModal(
            workHours = WorkHoursUiModel(
                startTime = LocalTime.of(11, 0),
                endTime = LocalTime.of(14, 0)
            ),
            onResetClick = {},
            onCloseClick = {},
            onApplyClick = {},
            modifier = Modifier,
        )
    }
}
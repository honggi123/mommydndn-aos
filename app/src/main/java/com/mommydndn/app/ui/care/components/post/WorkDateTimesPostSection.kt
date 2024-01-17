package com.mommydndn.app.ui.care.components.post

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.times
import com.mommydndn.app.R
import com.mommydndn.app.domain.model.WorkPeriod
import com.mommydndn.app.ui.components.chip.ClickableChip
import com.mommydndn.app.ui.components.range.RangeBox
import com.mommydndn.app.ui.theme.Grey200
import com.mommydndn.app.ui.theme.Grey50
import com.mommydndn.app.ui.theme.Grey500
import com.mommydndn.app.ui.theme.Grey600
import com.mommydndn.app.ui.theme.Salmon200
import com.mommydndn.app.ui.theme.Salmon300
import com.mommydndn.app.ui.theme.Salmon600
import com.mommydndn.app.ui.theme.White
import com.mommydndn.app.ui.theme.caption200
import com.mommydndn.app.ui.theme.paragraph300
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.Locale
import kotlin.math.max

@Composable
fun WorkDateTimesPostSection(
    selectedWorkPeriod: WorkPeriod,
    onWorkPeriodSelected: (WorkPeriod) -> Unit,
    selectedDates: List<LocalDate>,
    onAddDateClick: () -> Unit,
    onRemoveDateClick: (LocalDate) -> Unit,
    selectedDaysOfWeek: List<DayOfWeek>,
    onDayOfWeekClick: (DayOfWeek) -> Unit,
    startDate: LocalDate?,
    endDate: LocalDate?,
    onStartDateClick: () -> Unit,
    onEndDateClick: () -> Unit,
    startTime: LocalTime?,
    endTime: LocalTime?,
    onStartTimeClick: () -> Unit,
    onEndTimeClick: () -> Unit,
    negotiable: Boolean,
    onNegotiableClick: () -> Unit,
    modifier: Modifier = Modifier,
    title: String = stringResource(R.string.work_hours),
    subtitle: String = stringResource(R.string.required),
    workPeriods: List<WorkPeriod> = WorkPeriod.entries,
) {
    PostSection(
        title = title,
        subtitle = subtitle,
        modifier = modifier,
    ) {
        Column(
            modifier = Modifier,
            verticalArrangement = Arrangement.spacedBy(24.dp),
        ) {
            WorkPeriods(
                workPeriods = workPeriods,
                selectedWorkPeriod = selectedWorkPeriod,
                onClick = onWorkPeriodSelected,
                modifier = Modifier.fillMaxWidth()
            )

            when (selectedWorkPeriod) {
                WorkPeriod.OneTime -> {
                    OneTimeWorkDates(
                        onAddDateClick = onAddDateClick,
                        selectedDates = selectedDates,
                        onRemoveDateClick = onRemoveDateClick,
                        modifier = Modifier,
                    )
                }

                WorkPeriod.Regular -> {
                    RegularWorkDates(
                        selectedDaysOfWeek = selectedDaysOfWeek,
                        onDayOfWeekClick = onDayOfWeekClick,
                        startDate = startDate,
                        endDate = endDate,
                        onStartDateClick = onStartDateClick,
                        onEndDateClick = onEndDateClick,
                    )
                }
            }

            WorkTimes(
                startTime = startTime,
                endTime = endTime,
                onStartTimeClick = onStartTimeClick,
                onEndTimeClick = onEndTimeClick,
                negotiable = negotiable,
                onNegotiableClick = onNegotiableClick,
                modifier = Modifier,
            )
        }
    }
}

@Composable
private fun WorkPeriods(
    workPeriods: List<WorkPeriod>,
    selectedWorkPeriod: WorkPeriod,
    onClick: (WorkPeriod) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Row(
            modifier = Modifier.wrapContentSize(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            workPeriods.forEach { workPeriod ->
                ClickableChip(
                    text = workPeriod.displayName(),
                    selected = workPeriod == selectedWorkPeriod,
                    onClick = { onClick(workPeriod) },
                    modifier = Modifier.height(36.dp),
                )
            }
        }

        val description = if (selectedWorkPeriod == WorkPeriod.Regular) {
            stringResource(R.string.regular_work_description)
        } else {
            stringResource(R.string.one_time_work_description)
        }

        Text(
            text = description,
            style = MaterialTheme.typography.caption200.merge(
                color = Grey500,
                fontWeight = FontWeight.Normal,
            )
        )
    }
}

@Composable
private fun WorkPeriod.displayName(): String = when (this) {
    WorkPeriod.OneTime -> stringResource(R.string.short_term)
    WorkPeriod.Regular -> stringResource(R.string.regular)
}

@Composable
private fun OneTimeWorkDates(
    onAddDateClick: () -> Unit,
    selectedDates: List<LocalDate>,
    onRemoveDateClick: (LocalDate) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = stringResource(R.string.date),
            style = MaterialTheme.typography.paragraph300.merge(
                color = Grey500,
                fontWeight = FontWeight.Bold,
            )
        )

        Spacer(modifier = Modifier.height(6.dp))

        Button(
            onClick = onAddDateClick,
            modifier = Modifier.fillMaxWidth(),
            elevation = ButtonDefaults.elevation(defaultElevation = 0.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = Salmon200),
            contentPadding = PaddingValues(vertical = 12.dp),
        ) {
            Icon(
                painter = painterResource(id = R.drawable.icon_plus),
                contentDescription = "OneTimeDate_Plus",
                modifier = Modifier.size(24.dp),
                tint = Salmon600,
            )

            Spacer(modifier = Modifier.width(4.dp))

            Text(
                text = stringResource(R.string.add_date),
                style = MaterialTheme.typography.paragraph300.merge(
                    color = Salmon600,
                    fontWeight = FontWeight.Medium,
                    textAlign = TextAlign.Center,
                ),
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        val height = if (selectedDates.isEmpty()) {
            0.dp
        } else {
            val row = (selectedDates.size - 1) / 2 + 1

            row * 56.dp + max(row - 1, 0) * 8.dp
        }

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .fillMaxWidth()
                .height(height),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            val formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd")

            items(selectedDates.size) { index ->
                val date = selectedDates[index]

                Row(
                    modifier = Modifier
                        .height(56.dp)
                        .clip(shape = RoundedCornerShape(12.dp))
                        .background(color = Grey50)
                        .padding(start = 20.dp, end = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = formatter.format(date),
                        style = MaterialTheme.typography.paragraph300.merge(
                            color = Grey600,
                            fontWeight = FontWeight.Normal,
                        )
                    )

                    Image(
                        painter = painterResource(id = R.drawable.icon_circle_x_light_grey),
                        contentDescription = "SelectedDates_CircleXLightGrey",
                        modifier = Modifier
                            .size(size = 28.dp)
                            .clickable { onRemoveDateClick(date) }
                            .padding(3.5.dp),
                    )
                }
            }
        }
    }
}

@Composable
private fun DaysOfWeek(
    selectedDaysOfWeek: List<DayOfWeek>,
    onDayOfWeekClick: (DayOfWeek) -> Unit,
    modifier: Modifier = Modifier,
    daysOfWeek: List<DayOfWeek> = DayOfWeek.entries,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(6.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = stringResource(R.string.day_of_week),
            style = MaterialTheme.typography.paragraph300.merge(
                color = Grey500,
                fontWeight = FontWeight.Bold,
            )
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(horizontal = 4.dp, vertical = 12.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            daysOfWeek.forEach { dayOfWeek ->
                val selected = selectedDaysOfWeek.contains(dayOfWeek)

                Box(
                    contentAlignment = Alignment.Center,
                    modifier = modifier
                        .weight(1F)
                        .aspectRatio(1F)
                        .clip(shape = CircleShape)
                        .background(color = if (selected) Salmon200 else White)
                        .border(
                            border = BorderStroke(
                                width = 1.dp,
                                color = if (selected) Salmon300 else Grey200,
                            ),
                            shape = CircleShape
                        )
                        .clickable {
                            onDayOfWeekClick(dayOfWeek)
                        }
                ) {
                    Text(
                        text = dayOfWeek.getDisplayName(TextStyle.NARROW, Locale.KOREAN),
                        style = MaterialTheme.typography.caption200.merge(
                            color = Grey600,
                            fontWeight = FontWeight.Medium,
                        )
                    )
                }
            }
        }
    }
}

@Composable
private fun RegularWorkDates(
    selectedDaysOfWeek: List<DayOfWeek>,
    onDayOfWeekClick: (DayOfWeek) -> Unit,
    startDate: LocalDate?,
    endDate: LocalDate?,
    onStartDateClick: () -> Unit,
    onEndDateClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(24.dp),
    ) {
        DaysOfWeek(
            selectedDaysOfWeek = selectedDaysOfWeek,
            onDayOfWeekClick = onDayOfWeekClick,
            modifier = Modifier.fillMaxWidth(),
        )

        val formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd") // todo

        RangeBox(
            label = stringResource(id = R.string.date),
            option1Hint = stringResource(R.string.start_date),
            option2Hint = stringResource(R.string.end_date),
            onOption1Click = onStartDateClick,
            onOption2Click = onEndDateClick,
            modifier = Modifier.fillMaxWidth(),
            option1Text = startDate?.format(formatter).orEmpty(),
            option2Text = endDate?.format(formatter).orEmpty(),
        )
    }
}

@Composable
private fun WorkTimes(
    startTime: LocalTime?,
    endTime: LocalTime?,
    onStartTimeClick: () -> Unit,
    onEndTimeClick: () -> Unit,
    negotiable: Boolean,
    onNegotiableClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(6.dp),
    ) {
        val formatter = DateTimeFormatter.ofPattern("HH:mm") // todo

        RangeBox(
            label = stringResource(id = R.string.time),
            option1Hint = stringResource(id = R.string.start_time),
            option2Hint = stringResource(id = R.string.end_time),
            onOption1Click = onStartTimeClick,
            onOption2Click = onEndTimeClick,
            modifier = modifier,
            option1Text = startTime?.format(formatter).orEmpty(),
            option2Text = endTime?.format(formatter).orEmpty(),
        )

        Negotiable(
            checked = negotiable,
            onClick = onNegotiableClick,
            modifier = Modifier.wrapContentSize(),
        )
    }
}

@Composable
fun Negotiable(
    checked: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.clickable(
            interactionSource = remember { MutableInteractionSource() },
            indication = null,
            onClick = onClick,
        ),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            painter = painterResource(
                id = if (checked) {
                    R.drawable.icon_checked_mark
                } else {
                    R.drawable.icon_unchecked_mark
                }
            ),
            contentDescription = "Negotiable_check_mark",
            modifier = Modifier.size(32.dp),
            tint = if (checked) Salmon600 else Grey200
        )

        Text(
            text = stringResource(R.string.negotiable),
            modifier = Modifier.fillMaxWidth(),
            style = MaterialTheme.typography.paragraph300.copy(
                fontWeight = FontWeight.Normal,
                color = Grey600
            )
        )
    }
}

@Preview
@Composable
private fun WorkDateTimes_OneTime() {
    WorkDateTimesPostSection(
        selectedWorkPeriod = WorkPeriod.OneTime,
        onWorkPeriodSelected = {},
        selectedDates = emptyList(),
        onAddDateClick = {},
        onRemoveDateClick = {},
        selectedDaysOfWeek = emptyList(),
        onDayOfWeekClick = {},
        startDate = null,
        endDate = null,
        onStartDateClick = {},
        onEndDateClick = {},
        startTime = null,
        endTime = null,
        onStartTimeClick = {},
        onEndTimeClick = {},
        negotiable = false,
        onNegotiableClick = {},
        modifier = Modifier,
    )
}

@Preview
@Composable
private fun WorkDateTimes_OneTime_SelectedDates() {
    WorkDateTimesPostSection(
        selectedWorkPeriod = WorkPeriod.OneTime,
        onWorkPeriodSelected = {},
        selectedDates = buildList {
            add(LocalDate.now().plusDays(1))
            add(LocalDate.now().plusDays(3))
            add(LocalDate.now().plusDays(4))
        },
        onAddDateClick = {},
        onRemoveDateClick = {},
        selectedDaysOfWeek = emptyList(),
        onDayOfWeekClick = {},
        startDate = null,
        endDate = null,
        onStartDateClick = {},
        onEndDateClick = {},
        startTime = null,
        endTime = null,
        onStartTimeClick = {},
        onEndTimeClick = {},
        negotiable = false,
        onNegotiableClick = {},
        modifier = Modifier,
    )
}

@Preview
@Composable
private fun WorkDateTimes_Regular() {
    WorkDateTimesPostSection(
        selectedWorkPeriod = WorkPeriod.Regular,
        onWorkPeriodSelected = {},
        selectedDates = emptyList(),
        onAddDateClick = {},
        onRemoveDateClick = {},
        selectedDaysOfWeek = emptyList(),
        onDayOfWeekClick = {},
        startDate = null,
        endDate = null,
        onStartDateClick = {},
        onEndDateClick = {},
        startTime = null,
        endTime = null,
        onStartTimeClick = {},
        onEndTimeClick = {},
        negotiable = false,
        onNegotiableClick = {},
        modifier = Modifier,
    )
}

@Preview
@Composable
private fun Negotiable_NotChecked() {
    Negotiable(
        checked = false,
        onClick = {},
        modifier = Modifier
            .background(White)
            .padding(horizontal = 24.dp, vertical = 16.dp),
    )
}

@Preview
@Composable
private fun Negotiable_Checked() {
    Negotiable(
        checked = true,
        onClick = {},
        modifier = Modifier
            .background(White)
            .padding(horizontal = 24.dp, vertical = 16.dp),
    )
}
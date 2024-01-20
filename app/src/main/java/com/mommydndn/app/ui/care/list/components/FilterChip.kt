package com.mommydndn.app.ui.care.list.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mommydndn.app.R
import com.mommydndn.app.domain.model.CareType
import com.mommydndn.app.domain.model.WorkHours
import com.mommydndn.app.ui.care.list.filter.CareTypeFilter
import com.mommydndn.app.ui.care.list.filter.DayOfWeekFilter
import com.mommydndn.app.ui.care.list.filter.WorkHoursFilter
import com.mommydndn.app.ui.care.list.filter.WorkPeriodFilter
import com.mommydndn.app.ui.theme.Grey100
import com.mommydndn.app.ui.theme.Grey400
import com.mommydndn.app.ui.theme.Grey600
import com.mommydndn.app.ui.theme.Grey700
import com.mommydndn.app.ui.theme.Grey800
import com.mommydndn.app.ui.theme.Salmon200
import com.mommydndn.app.ui.theme.Salmon300
import com.mommydndn.app.ui.theme.White
import com.mommydndn.app.ui.theme.caption200
import java.time.DayOfWeek
import java.time.LocalTime

data class FilterUiModel(
    val selected: Boolean,
    val text: String,
)

@Composable
fun FilterChip(
    selected: Boolean,
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val backgroundColor: Color = if (selected) {
        Salmon200
    } else {
        White
    }

    val borderColor: Color = if (selected) {
        Salmon300
    } else {
        Grey100
    }

    val textColor: Color = if (selected) {
        Grey800
    } else {
        Grey700
    }

    val fontWeight: FontWeight = if (selected) {
        FontWeight.Bold
    } else {
        FontWeight.Medium
    }

    val iconTint: Color = if (selected) {
        Grey600
    } else {
        Grey400
    }

    Box(modifier = modifier) {
        Row(
            modifier = Modifier
                .background(backgroundColor, RoundedCornerShape(20.dp))
                .border(1.dp, borderColor, RoundedCornerShape(20.dp))
                .clip(RoundedCornerShape(20.dp))
                .clickable(onClick = onClick)
                .padding(start = 12.dp, end = 6.dp, top = 6.dp, bottom = 6.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = text,
                color = textColor,
                style = MaterialTheme.typography.caption200.merge(
                    fontWeight = fontWeight,
                )
            )

            Icon(
                painter = painterResource(id = R.drawable.icon_arrow_down),
                contentDescription = "FilterChip_ArrowDown",
                modifier = Modifier.size(24.dp),
                tint = iconTint,
            )
        }
    }
}


@Preview
@Composable
private fun CareTypesFilterChipPreview() {
    Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
        with(CareTypeFilter()) {
            FilterChip(
                selected = selected,
                text = displayName(),
                onClick = {}
            )
        }

        val careTypes = listOf(
            CareType.ChildCare,
            CareType.SchoolTransportation,
            CareType.Housekeeping,
        )

        with(CareTypeFilter(careTypes = careTypes)) {
            FilterChip(
                selected = selected,
                text = displayName(),
                onClick = {}
            )
        }
    }
}

@Preview
@Composable
private fun DaysOfWeekFilterChipPreview() {
    Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
        with(DayOfWeekFilter()) {
            FilterChip(
                selected = selected,
                text = displayName(),
                onClick = {}
            )
        }

        val daysOfWeek = listOf(
            DayOfWeek.TUESDAY,
            DayOfWeek.FRIDAY,
            DayOfWeek.WEDNESDAY,
            DayOfWeek.SATURDAY,
        )

        with(DayOfWeekFilter(daysOfWeek = daysOfWeek)) {
            FilterChip(
                selected = selected,
                text = displayName(),
                onClick = {}
            )
        }
    }
}

@Preview
@Composable
private fun WorkHoursFilterChipPreview() {
    Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
        with(WorkHoursFilter()) {
            FilterChip(
                selected = selected,
                text = displayName(),
                onClick = {}
            )
        }

        val workHours = WorkHours(
            LocalTime.of(12, 0),
            LocalTime.of(18, 30),
        )

        with(WorkHoursFilter(workHours)) {
            FilterChip(
                selected = selected,
                text = displayName(),
                onClick = {}
            )
        }
    }
}

@Preview
@Composable
private fun WorkPeriodFilterChipPreview() {
    Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
        with(WorkPeriodFilter()) {
            FilterChip(
                selected = selected,
                text = displayName(),
                onClick = {}
            )
        }

        with(WorkPeriodFilter(WorkPeriodUiModel.OneTime)) {
            FilterChip(
                selected = selected,
                text = displayName(),
                onClick = {}
            )
        }

        with(WorkPeriodFilter(WorkPeriodUiModel.Regular)) {
            FilterChip(
                selected = selected,
                text = displayName(),
                onClick = {}
            )
        }
    }
}
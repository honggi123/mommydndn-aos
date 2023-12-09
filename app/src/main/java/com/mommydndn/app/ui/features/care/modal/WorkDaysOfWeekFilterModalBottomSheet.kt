package com.mommydndn.app.ui.features.care.modal

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mommydndn.app.R
import com.mommydndn.app.ui.features.care.filters.DaysOfWeekFilter
import com.mommydndn.app.ui.theme.Grey200
import com.mommydndn.app.ui.theme.Grey600
import com.mommydndn.app.ui.theme.Salmon200
import com.mommydndn.app.ui.theme.Salmon300
import com.mommydndn.app.ui.theme.White
import com.mommydndn.app.ui.theme.caption200
import java.time.DayOfWeek
import java.time.format.TextStyle
import java.util.Locale

@Composable
internal fun WorkDaysOfWeekFilterModalBottomSheet(
    daysOfWeekFilter: DaysOfWeekFilter,
    onRefreshClick: () -> Unit,
    onCloseClick: () -> Unit,
    onUpdateClick: (DaysOfWeekFilter) -> Unit,
    modifier: Modifier = Modifier,
) {
    val selectedDaysOfWeek = remember {
        mutableStateOf(daysOfWeekFilter.daysOfWeek)
    }

    CareFilterModalBottomSheet(
        onCloseClick = onCloseClick,
        onUpdateClick = {
            onUpdateClick(
                DaysOfWeekFilter(
                    daysOfWeek = selectedDaysOfWeek.value?.takeIf {
                        it.isNotEmpty()
                    },
                )
            )
        },
        modifier = modifier,
    ) {
        ModalBottomSheetTitleWithRefreshButton(
            title = stringResource(id = R.string.time),
            onRefreshClick = onRefreshClick,
            modifier = Modifier,
        )

        ModalBottomSheetHorizontalDivider(
            modifier = Modifier.padding(vertical = 12.dp)
        )

        // todo: tablet ui
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            DayOfWeek.values().forEach { dayOfWeek ->
                ModalBottomSheetSelectableButton(
                    selected = selectedDaysOfWeek.value?.contains(dayOfWeek) == true,
                    selectedBorderColor = Salmon300,
                    selectedBackgroundColor = Salmon200,
                    unselectedBorderColor = Grey200,
                    unselectedBackgroundColor = White,
                    onClick = { selected ->
                        selectedDaysOfWeek.value = selectedDaysOfWeek.value
                            .orEmpty()
                            .toMutableList()
                            .apply {
                                if (selected) {
                                    add(dayOfWeek)
                                } else {
                                    remove(dayOfWeek)
                                }
                            }
                            .takeIf { it.isNotEmpty() }
                    },
                    modifier = Modifier
                        .weight(1F)
                        .padding(horizontal = 4.dp)
                        .aspectRatio(1F),
                ) {
                    Text(
                        text = dayOfWeek.getDisplayName(TextStyle.NARROW, Locale.KOREAN),
                        modifier = Modifier
                            .align(Alignment.Center),
                        style = MaterialTheme.typography.caption200.merge(
                            color = Grey600,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Medium,
                            textAlign = TextAlign.Center,
                        )
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun WorkDaysOfWeekFilter_EmptyDaysOfWeek() {
    WorkDaysOfWeekFilterModalBottomSheet(
        daysOfWeekFilter = DaysOfWeekFilter(
            daysOfWeek = emptyList(), // null,
        ),
        onRefreshClick = {},
        onCloseClick = {},
        onUpdateClick = {},
        modifier = Modifier
    )
}

@Preview
@Composable
private fun WorkDaysOfWeekFilter_WithDaysOfWeek() {
    WorkDaysOfWeekFilterModalBottomSheet(
        daysOfWeekFilter = DaysOfWeekFilter(
            daysOfWeek = listOf(
                DayOfWeek.MONDAY,
                DayOfWeek.THURSDAY,
                DayOfWeek.SATURDAY,
                DayOfWeek.SUNDAY,
            ),
        ),
        onRefreshClick = {},
        onCloseClick = {},
        onUpdateClick = {},
        modifier = Modifier
    )
}
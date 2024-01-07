package com.mommydndn.app.feature.care.components.filter

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mommydndn.app.R
import com.mommydndn.app.ui.theme.Grey200
import com.mommydndn.app.ui.theme.Grey600
import com.mommydndn.app.ui.theme.Salmon200
import com.mommydndn.app.ui.theme.Salmon300
import com.mommydndn.app.ui.theme.Typography
import com.mommydndn.app.ui.theme.White
import com.mommydndn.app.ui.theme.caption200
import java.time.DayOfWeek
import java.time.format.TextStyle
import java.util.Locale

@Composable
internal fun WorkDaysOfWeekFilterModal(
    selectedDaysOfWeek: List<DayOfWeek>,
    onRefreshClick: () -> Unit,
    onCloseClick: () -> Unit,
    onUpdateClick: (List<DayOfWeek>) -> Unit,
    modifier: Modifier = Modifier,
) {
    var currentSelectedDaysOfWeek by remember {
        mutableStateOf(selectedDaysOfWeek)
    }

    CareFilterModal(
        onCloseClick = onCloseClick,
        onApplyClick = {
            onUpdateClick(currentSelectedDaysOfWeek)
        },
        modifier = modifier,
    ) {
        ModalTitleWithRefreshButton(
            title = stringResource(id = R.string.time),
            onRefreshClick = onRefreshClick,
            modifier = Modifier,
        )

        ModalHorizontalDivider(Modifier.padding(vertical = 12.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            DayOfWeek.entries.forEach { dayOfWeek ->
                ModalBottomSheetSelectableButton(
                    selected = currentSelectedDaysOfWeek.contains(dayOfWeek),
                    selectedBorderColor = Salmon300,
                    selectedBackgroundColor = Salmon200,
                    unselectedBorderColor = Grey200,
                    unselectedBackgroundColor = White,
                    onClick = { selected ->
                        currentSelectedDaysOfWeek = currentSelectedDaysOfWeek.toMutableList().apply {
                            if (selected) {
                                add(dayOfWeek)
                            } else {
                                remove(dayOfWeek)
                            }
                        }
                    },
                    modifier = Modifier
                        .weight(1F)
                        .padding(horizontal = 4.dp)
                        .aspectRatio(1F),
                ) {
                    Text(
                        text = dayOfWeek.getDisplayName(TextStyle.NARROW, Locale.KOREAN),
                        modifier = Modifier.align(Alignment.Center),
                        color = Grey600,
                        textAlign = TextAlign.Center,
                        style = Typography.caption200.merge(
                            fontWeight = FontWeight.Medium,
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
    WorkDaysOfWeekFilterModal(
        selectedDaysOfWeek = emptyList(),
        onRefreshClick = {},
        onCloseClick = {},
        onUpdateClick = {},
        modifier = Modifier,
    )
}

@Preview
@Composable
private fun WorkDaysOfWeekFilter_WithDaysOfWeek() {
    WorkDaysOfWeekFilterModal(
        selectedDaysOfWeek = listOf(
            DayOfWeek.MONDAY,
            DayOfWeek.THURSDAY,
            DayOfWeek.SATURDAY,
            DayOfWeek.SUNDAY,
        ),
        onRefreshClick = {},
        onCloseClick = {},
        onUpdateClick = {},
        modifier = Modifier
    )
}
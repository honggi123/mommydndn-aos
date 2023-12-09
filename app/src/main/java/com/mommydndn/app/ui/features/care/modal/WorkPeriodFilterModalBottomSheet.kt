package com.mommydndn.app.ui.features.care.modal

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mommydndn.app.R
import com.mommydndn.app.domain.model.care.WorkPeriod
import com.mommydndn.app.ui.features.care.filters.WorkPeriodFilter
import com.mommydndn.app.ui.theme.Grey200
import com.mommydndn.app.ui.theme.Grey700
import com.mommydndn.app.ui.theme.Salmon600
import com.mommydndn.app.ui.theme.paragraph300
import com.mommydndn.app.ui.theme.paragraph400

@Composable
internal fun WorkPeriodFilterModalBottomSheet(
    workPeriodFilter: WorkPeriodFilter,
    onCloseClick: () -> Unit,
    onUpdateClick: (WorkPeriodFilter) -> Unit,
    modifier: Modifier = Modifier,
) {
    val filter = remember {
        mutableStateOf(workPeriodFilter)
    }

    CareFilterModalBottomSheet(
        onCloseClick = onCloseClick,
        onUpdateClick = {
            onUpdateClick(filter.value)
        },
        modifier = modifier,
    ) {
        // todo: modal_bottom_sheet_title
        Text(
            text = stringResource(R.string.care_work_period),
            style = MaterialTheme.typography.paragraph400.merge(
                color = Grey700,
                fontWeight = FontWeight.Bold,
            ),
        )

        ModalBottomSheetHorizontalDivider(
            modifier = Modifier.padding(vertical = 12.dp),
        )

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(6.dp),
        ) {
            val workPeriods = buildList(capacity = 3) {
                add(null)

                addAll(WorkPeriod.values())
            }

            workPeriods.forEach { workPeriod ->
                SelectableRadioButton(
                    selected = filter.value.workPeriod == workPeriod,
                    text = workPeriod.displayName(),
                    onSelected = {
                        filter.value = filter.value.copy(
                            workPeriod = workPeriod
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                )
            }
        }
    }
}

@Composable
private fun WorkPeriod?.displayName(): String = when (this) {
    WorkPeriod.ONE_TIME -> stringResource(R.string.one_time)
    WorkPeriod.REGULAR -> stringResource(R.string.regular)
    else -> stringResource(R.string.all)
}

@Composable
private fun SelectableRadioButton(
    selected: Boolean,
    text: String,
    onSelected: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.clickable(onClick = onSelected),
        horizontalArrangement = Arrangement.spacedBy(6.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            painter = painterResource(
                id = if (selected) {
                    R.drawable.icon_selected_radio
                } else {
                    R.drawable.icon_unselected_radio
                }
            ),
            contentDescription = "SelectableRadioButton_Radio",
            modifier = Modifier.size(32.dp),
            tint = if (selected) {
                Salmon600
            } else {
                Grey200
            }
        )

        Text(
            text = text,
            modifier = Modifier.weight(1F),
            style = MaterialTheme.typography.paragraph300.copy(
                color = Grey700,
                fontWeight = FontWeight.Normal,
            ),
        )
    }
}

@Preview
@Composable
private fun AllWorkPeriodsFilter() {
    WorkPeriodFilterModalBottomSheet(
        workPeriodFilter = WorkPeriodFilter(
            workPeriod = null,
        ),
        onCloseClick = {},
        onUpdateClick = {},
        modifier = Modifier,
    )
}

@Preview
@Composable
private fun OneTimeWorkPeriodFilter() {
    WorkPeriodFilterModalBottomSheet(
        workPeriodFilter = WorkPeriodFilter(
            workPeriod = WorkPeriod.ONE_TIME,
        ),
        onCloseClick = {},
        onUpdateClick = {},
        modifier = Modifier,
    )
}

@Preview
@Composable
private fun RegularWorkPeriodFilter() {
    WorkPeriodFilterModalBottomSheet(
        workPeriodFilter = WorkPeriodFilter(
            workPeriod = WorkPeriod.REGULAR,
        ),
        onCloseClick = {},
        onUpdateClick = {},
        modifier = Modifier,
    )
}
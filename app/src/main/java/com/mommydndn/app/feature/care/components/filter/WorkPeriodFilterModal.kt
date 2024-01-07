package com.mommydndn.app.feature.care.components.filter

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mommydndn.app.R
import com.mommydndn.app.ui.theme.Grey200
import com.mommydndn.app.ui.theme.Grey700
import com.mommydndn.app.ui.theme.Salmon600
import com.mommydndn.app.ui.theme.paragraph300
import com.mommydndn.app.ui.theme.paragraph400

enum class WorkPeriodUiModel {
    ALL, ONE_TIME, REGULAR
}

@Composable
private fun WorkPeriodUiModel.displayName(): String = when (this) {
    WorkPeriodUiModel.ALL ->  stringResource(R.string.all)
    WorkPeriodUiModel.ONE_TIME -> stringResource(R.string.one_time)
    WorkPeriodUiModel.REGULAR -> stringResource(R.string.regular)
}

@Composable
internal fun WorkPeriodFilterModal(
    workPeriod: WorkPeriodUiModel,
    onCloseClick: () -> Unit,
    onApplyClick: (WorkPeriodUiModel) -> Unit,
    modifier: Modifier = Modifier,
    allWorkPeriods: List<WorkPeriodUiModel> = WorkPeriodUiModel.entries,
) {
    var currentWorkPeriod by remember {
        mutableStateOf(workPeriod)
    }

    CareFilterModal(
        onCloseClick = onCloseClick,
        onApplyClick = {
            onApplyClick(currentWorkPeriod)
        },
        modifier = modifier,
    ) {
        Text(
            text = stringResource(R.string.care_work_period),
            color = Grey700,
            style = MaterialTheme.typography.paragraph400.merge(
                fontWeight = FontWeight.Bold,
            ),
        )

        ModalHorizontalDivider(Modifier.padding(vertical = 12.dp))

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(6.dp),
        ) {
            allWorkPeriods.forEach { workPeriod ->
                SelectableRadioButton(
                    selected = workPeriod == currentWorkPeriod,
                    text = workPeriod.displayName(),
                    onSelected = {
                        currentWorkPeriod = workPeriod
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
private fun WorkPeriodFilterPreview() {
    Column(
        modifier = Modifier.verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        WorkPeriodFilterModal(
            workPeriod = WorkPeriodUiModel.ALL,
            onCloseClick = {},
            onApplyClick = {},
            modifier = Modifier,
        )

        WorkPeriodFilterModal(
            workPeriod = WorkPeriodUiModel.ONE_TIME,
            onCloseClick = {},
            onApplyClick = {},
            modifier = Modifier,
        )

        WorkPeriodFilterModal(
            workPeriod = WorkPeriodUiModel.REGULAR,
            onCloseClick = {},
            onApplyClick = {},
            modifier = Modifier,
        )
    }
}
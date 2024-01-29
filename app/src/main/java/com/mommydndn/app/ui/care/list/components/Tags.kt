package com.mommydndn.app.ui.care.list.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mommydndn.app.R
import com.mommydndn.app.domain.model.CareType
import com.mommydndn.app.domain.model.WorkPeriod
import com.mommydndn.app.ui.components.tag.TagLabel
import com.mommydndn.app.ui.theme.Blue50
import com.mommydndn.app.ui.theme.Blue600
import com.mommydndn.app.ui.theme.DeepOrange
import com.mommydndn.app.ui.theme.Grey100
import com.mommydndn.app.ui.theme.Grey600
import com.mommydndn.app.ui.theme.Orange100

@Composable
fun WorkPeriodTag(
    workPeriod: WorkPeriod,
    modifier: Modifier = Modifier,
) {
    TagLabel(
        text = workPeriod.displayName(),
        backgroundColor = Blue50,
        textColor = Blue600,
        modifier = modifier,
    )
}

@Composable
fun CareTypeTag(
    careType: CareType,
    modifier: Modifier = Modifier,
) {
    TagLabel(
        text = careType.displayName,
        textColor = DeepOrange,
        backgroundColor = Orange100,
        modifier = modifier,
    )
}

@Composable
fun ClosedCareJobTag(
    modifier: Modifier = Modifier,
) {
    TagLabel(
        text = stringResource(R.string.closed),
        textColor = Grey600,
        backgroundColor = Grey100,
        modifier = modifier
    )
}

@Preview
@Composable
private fun TagsPreview() {
    Row(horizontalArrangement = Arrangement.spacedBy(3.dp)) {
        WorkPeriodTag(
            workPeriod = WorkPeriod.Regular,
            modifier = Modifier,
        )

        CareTypeTag(
            careType = CareType.SeniorCare,
            modifier = Modifier,
        )

        CareTypeTag(
            careType = CareType.SchoolTransportation,
            modifier = Modifier,
        )

        ClosedCareJobTag(modifier = Modifier)
    }
}
package com.mommydndn.app.ui.care.list.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mommydndn.app.R
import com.mommydndn.app.domain.model.CareType
import com.mommydndn.app.domain.model.WorkPeriod
import com.mommydndn.app.ui.theme.Blue50
import com.mommydndn.app.ui.theme.Blue600
import com.mommydndn.app.ui.theme.DeepOrange
import com.mommydndn.app.ui.theme.Grey100
import com.mommydndn.app.ui.theme.Grey600
import com.mommydndn.app.ui.theme.Orange100
import com.mommydndn.app.ui.theme.caption100

@Composable
fun WorkPeriodTag(
    workPeriod: WorkPeriod,
    modifier: Modifier = Modifier,
) {
    CareJobTag(
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
    CareJobTag(
        text = careType.displayName,
        backgroundColor = Orange100,
        textColor = DeepOrange,
        modifier = modifier,
    )
}

@Composable
fun ClosedCareJobTag(
    modifier: Modifier = Modifier,
) {
    CareJobTag(
        text = stringResource(R.string.closed),
        backgroundColor = Grey100,
        textColor = Grey600,
        modifier = modifier,
    )
}

@Composable
fun CareJobTag(
    text: String,
    backgroundColor: Color,
    textColor: Color,
    modifier: Modifier = Modifier,
) {
    Text(
        text = text,
        modifier = modifier
            .background(backgroundColor, shape = RoundedCornerShape(6.dp))
            .padding(vertical = 4.dp, horizontal = 8.dp),
        style = MaterialTheme.typography.caption100.merge(
            color = textColor,
            fontWeight = FontWeight.Medium,
        )
    )
}

@Preview
@Composable
private fun CareJobTagsPreview() {
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
    }
}

@Preview
@Composable
private fun ClosedCareJobTagPreview() {
    ClosedCareJobTag(modifier = Modifier)
}
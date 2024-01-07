package com.mommydndn.app.ui.care.jobopening

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
import com.mommydndn.app.domain.model.care.CareType
import com.mommydndn.app.domain.model.care.WorkPeriod
import com.mommydndn.app.feature.care.screen.displayName
import com.mommydndn.app.ui.theme.Blue50
import com.mommydndn.app.ui.theme.Blue600
import com.mommydndn.app.ui.theme.DeepOrange
import com.mommydndn.app.ui.theme.Grey100
import com.mommydndn.app.ui.theme.Grey600
import com.mommydndn.app.ui.theme.Orange100
import com.mommydndn.app.ui.theme.caption100

@Composable
internal fun CareJobOpeningWorkPeriod(
    workPeriod: WorkPeriod,
    modifier: Modifier = Modifier,
) {
    CareJobOpeningTag(
        text = workPeriod.displayName(),
        backgroundColor = Blue50,
        textColor = Blue600,
        modifier = modifier,
    )
}

@Composable
internal fun CareJobOpeningCareType(
    careType: CareType,
    modifier: Modifier = Modifier,
) {
    CareJobOpeningTag(
        text = careType.displayName(),
        backgroundColor = Orange100,
        textColor = DeepOrange,
        modifier = modifier,
    )
}

@Composable
internal fun ClosedCareJobOpening(
    modifier: Modifier = Modifier,
) {
    CareJobOpeningTag(
        text = stringResource(R.string.closed),
        backgroundColor = Grey100,
        textColor = Grey600,
        modifier = modifier,
    )
}

@Composable
internal fun CareJobOpeningTag(
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
private fun CareJobOpeningTagsPreview() {
    Row(horizontalArrangement = Arrangement.spacedBy(3.dp)) {
        CareJobOpeningWorkPeriod(
            workPeriod = WorkPeriod.REGULAR,
            modifier = Modifier,
        )

        CareJobOpeningCareType(
            careType = CareType.SENIOR_CARE,
            modifier = Modifier,
        )

        CareJobOpeningCareType(
            careType = CareType.SCHOOL_TRANSPORTATION,
            modifier = Modifier,
        )
    }
}

@Preview
@Composable
private fun ClosedCareJobOpeningTagPreview() {
    ClosedCareJobOpening(modifier = Modifier)
}
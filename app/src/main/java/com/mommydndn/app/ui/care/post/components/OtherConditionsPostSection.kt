package com.mommydndn.app.ui.care.post.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.times
import com.mommydndn.app.R
import com.mommydndn.app.domain.model.CareAgencyOtherCondition
import com.mommydndn.app.domain.model.CareWorkerOtherCondition
import com.mommydndn.app.domain.model.OtherCondition
import com.mommydndn.app.ui.components.check.CheckboxListItem

@Composable
fun OtherConditionsPostSection(
    otherConditions: List<OtherCondition>,
    selectedConditions: List<OtherCondition>,
    onClick: (OtherCondition) -> Unit,
    modifier: Modifier = Modifier,
    title: String = stringResource(R.string.other_conditions),
    subtitle: String = stringResource(id = R.string.optional),
) {
    PostSection(
        title = title,
        subtitle = subtitle,
        modifier = modifier,
    ) {
        val rowCount = (otherConditions.size + 1) / 2
        val height = rowCount * 32.dp + (rowCount - 1) * 8.dp

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .fillMaxWidth()
                .height(height),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            items(otherConditions) { condition ->
                val checked = selectedConditions.contains(condition)

                CheckboxListItem(
                    checked = checked,
                    onClick = {
                        onClick(condition)
                    },
                    text = condition.displayName,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                )
            }
        }
    }
}

private val OtherCondition.displayName: String
    @Composable
    get() = when (this) {
        CareWorkerOtherCondition.CCTV -> stringResource(R.string.cctv_okay)
        CareWorkerOtherCondition.Residential -> stringResource(R.string.occupancy_available)
        CareWorkerOtherCondition.Pets -> stringResource(R.string.pets_okay)
        CareWorkerOtherCondition.NoReligion -> stringResource(R.string.atheism)
        CareWorkerOtherCondition.NonSmoker -> stringResource(R.string.non_smoker)
        CareAgencyOtherCondition.AS -> stringResource(R.string.guarantee_as)
    }

@Preview
@Composable
private fun EmptyOtherConditionsPreview() {
    OtherConditionsPostSection(
        otherConditions = CareWorkerOtherCondition.entries,
        selectedConditions = emptyList(),
        onClick = {},
        modifier = Modifier,
    )
}

@Preview
@Composable
private fun OtherConditionsPreview() {
    OtherConditionsPostSection(
        otherConditions = CareWorkerOtherCondition.entries,
        selectedConditions = listOf(
            CareWorkerOtherCondition.Pets,
            CareWorkerOtherCondition.NonSmoker,
            CareWorkerOtherCondition.NoReligion,
        ),
        onClick = {},
        modifier = Modifier,
    )
}

@Preview
@Composable
private fun SingleOtherConditionPreview() {
    OtherConditionsPostSection(
        otherConditions = CareAgencyOtherCondition.entries,
        selectedConditions = listOf(CareAgencyOtherCondition.AS),
        onClick = {},
        modifier = Modifier,
    )
}
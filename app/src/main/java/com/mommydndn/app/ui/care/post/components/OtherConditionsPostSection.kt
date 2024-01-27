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
import com.mommydndn.app.domain.model.CaregiverPreference
import com.mommydndn.app.ui.components.check.CheckboxListItem
import java.util.Collections

@Composable
fun PreferencesPostSection(
    preferences: List<CaregiverPreference>,
    selectedPreferences: List<CaregiverPreference>,
    onClick: (CaregiverPreference) -> Unit,
    modifier: Modifier = Modifier,
    title: String = stringResource(R.string.other_conditions),
    subtitle: String = stringResource(id = R.string.optional),
) {
    PostSection(
        title = title,
        subtitle = subtitle,
        modifier = modifier,
    ) {
        val rowCount = (preferences.size + 1) / 2
        val height = rowCount * 32.dp + (rowCount - 1) * 8.dp

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .fillMaxWidth()
                .height(height),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            items(preferences) { condition ->
                val checked = selectedPreferences.contains(condition)

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

private val CaregiverPreference.displayName: String
    @Composable
    get() = when (this) {
        CaregiverPreference.PetFriendly -> stringResource(R.string.pets_okay)
        CaregiverPreference.CctvAllowed -> stringResource(R.string.cctv_okay)
        CaregiverPreference.MoveInAvailable -> stringResource(R.string.occupancy_available)
        CaregiverPreference.NonSmoker -> stringResource(R.string.non_smoker)
        CaregiverPreference.NonReligious -> stringResource(R.string.atheism)
    }

@Composable
fun OtherConditionsPostSection(
    selectedConditions: List<CareAgencyOtherCondition>,
    onClick: (CareAgencyOtherCondition) -> Unit,
    modifier: Modifier = Modifier,
    otherConditions: List<CareAgencyOtherCondition> = Collections.singletonList(CareAgencyOtherCondition.AfterSalesGuranteed),
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

private val CareAgencyOtherCondition.displayName: String
    @Composable
    get() = when (this) {
        CareAgencyOtherCondition.AfterSalesGuranteed -> stringResource(R.string.guarantee_as)
    }

@Preview
@Composable
private fun PreferencesPreview() {
    PreferencesPostSection(
        preferences = CaregiverPreference.entries,
        selectedPreferences = listOf(
            CaregiverPreference.CctvAllowed,
            CaregiverPreference.PetFriendly,
            CaregiverPreference.MoveInAvailable,
        ),
        onClick = {},
        modifier = Modifier,
    )
}

@Preview
@Composable
private fun OtherConditionsPreview() {
    OtherConditionsPostSection(
        otherConditions = listOf(CareAgencyOtherCondition.AfterSalesGuranteed),
        selectedConditions = listOf(CareAgencyOtherCondition.AfterSalesGuranteed),
        onClick = {},
        modifier = Modifier,
    )
}
package com.mommydndn.app.feature.care.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mommydndn.app.R
import com.mommydndn.app.domain.model.care.CareType
import com.mommydndn.app.ui.components.chip.ClickableChip
import com.mommydndn.app.feature.care.screen.displayName

@Composable
internal fun CareTypesPostField(
    selectedCareTypes: List<CareType>,
    onClick: (CareType) -> Unit,
    modifier: Modifier = Modifier,
    title: String = stringResource(R.string.wanted_care_types),
    subtitle: String = stringResource(id = R.string.required),
    careTypes: List<CareType> = CareType.values().toList(),
) {
    PostField(
        title = title,
        subtitle = subtitle,
        modifier = modifier,
    ) {
        ClickableCareTypeChipsRow(
            selectedCareTypes = selectedCareTypes,
            onClick = onClick,
            modifier = Modifier,
            careTypes = careTypes,
        )
    }
}

@Composable
internal fun ClickableCareTypeChipsRow(
    selectedCareTypes: List<CareType>,
    onClick: (CareType) -> Unit,
    modifier: Modifier = Modifier,
    careTypes: List<CareType> = CareType.values().toList(),
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        careTypes.forEach { careType ->
            ClickableChip(
                text = careType.displayName(),
                selected = selectedCareTypes.contains(careType),
                onClick = { onClick(careType) },
                modifier = Modifier.height(36.dp),
            )
        }
    }
}

@Preview
@Composable
private fun EmptySelectedCareTypes() {
    CareTypesPostField(
        selectedCareTypes = emptyList(),
        onClick = {},
        modifier = Modifier,
    )
}

@Preview
@Composable
private fun SelectedCareTypes() {
    CareTypesPostField(
        selectedCareTypes = buildList {
            add(CareType.SCHOOL_TRANSPORTATION)
            add(CareType.HOUSEKEEPING)
        },
        onClick = {},
        modifier = Modifier,
    )
}
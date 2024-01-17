package com.mommydndn.app.ui.care.components.post

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mommydndn.app.R
import com.mommydndn.app.domain.model.CareType
import com.mommydndn.app.ui.care.displayName
import com.mommydndn.app.ui.components.chip.ClickableChip

@Composable
fun CareTypesPostSection(
    selectedCareTypes: List<CareType>,
    onCareTypeClick: (CareType) -> Unit,
    modifier: Modifier = Modifier,
    title: String = stringResource(R.string.wanted_care_types),
    subtitle: String = stringResource(id = R.string.required),
    careTypes: List<CareType> = CareType.entries,
) {
    PostSection(
        title = title,
        subtitle = subtitle,
        modifier = modifier,
    ) {
        ClickableCareTypeChipsRow(
            selectedCareTypes = selectedCareTypes,
            onClick = onCareTypeClick,
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
    careTypes: List<CareType> = CareType.entries,
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
    CareTypesPostSection(
        selectedCareTypes = emptyList(),
        onCareTypeClick = {},
        modifier = Modifier,
    )
}

@Preview
@Composable
private fun SelectedCareTypes() {
    CareTypesPostSection(
        selectedCareTypes = listOf(
            CareType.SchoolTransportation,
            CareType.Housekeeping
        ),
        onCareTypeClick = {},
        modifier = Modifier,
    )
}
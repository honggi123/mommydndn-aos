package com.mommydndn.app.ui.features.care.jobopening.post.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mommydndn.app.R
import com.mommydndn.app.domain.model.care.CareType
import com.mommydndn.app.ui.components.chip.ClickableChip
import com.mommydndn.app.ui.features.care.displayName
import com.mommydndn.app.ui.theme.White

@Composable
internal fun PostCareJobOpeningCareTypes(
    selectedCareTypes: List<CareType>,
    onCareTypeClick: (CareType) -> Unit,
    modifier: Modifier = Modifier,
    careTypes: List<CareType> = CareType.values().toList(),
) {
    Column(
        modifier = modifier
            .background(White)
            .padding(start = 24.dp, top = 28.dp, end = 24.dp, bottom = 40.dp),
        verticalArrangement = Arrangement.spacedBy(28.dp)
    ) {
        PostFieldTitle(
            title = stringResource(R.string.needed_care),
            subtitle = stringResource(R.string.required),
            modifier = Modifier,
        )

        ClickableCareTypeChipsRow(
            selectedCareTypes = selectedCareTypes,
            onCareTypeSelected = onCareTypeClick,
            modifier = Modifier,
            careTypes = careTypes,
        )
    }
}

@Composable
internal fun ClickableCareTypeChipsRow(
    selectedCareTypes: List<CareType>,
    onCareTypeSelected: (CareType) -> Unit,
    modifier: Modifier = Modifier,
    careTypes: List<CareType> = CareType.values().toList(),
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        careTypes.forEach { careType ->
            ClickableChip(modifier = Modifier.height(36.dp),
                text = careType.displayName(),
                selected = selectedCareTypes.contains(careType),
                onClick = { onCareTypeSelected(careType) })
        }
    }
}

@Preview
@Composable
private fun CareTypes_NotSelected() {
    PostCareJobOpeningCareTypes(
        careTypes = CareType.values().toList(),
        selectedCareTypes = emptyList(),
        onCareTypeClick = {},
        modifier = Modifier,
    )
}

@Preview
@Composable
private fun CareTypes_Selected() {
    PostCareJobOpeningCareTypes(
        careTypes = CareType.values().toList(),
        selectedCareTypes = listOf(CareType.HOUSEKEEPING, CareType.SCHOOL_TRANSPORTATION),
        onCareTypeClick = {},
        modifier = Modifier,
    )
}
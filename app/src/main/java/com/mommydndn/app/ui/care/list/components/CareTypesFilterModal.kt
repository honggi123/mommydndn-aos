package com.mommydndn.app.ui.care.list.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mommydndn.app.R
import com.mommydndn.app.domain.model.CareType
import com.mommydndn.app.ui.components.check.CheckListItem
import com.mommydndn.app.ui.components.check.CheckboxListItem

@Composable
fun CareTypeFilterModal(
    selectedCareTypes: List<CareType>,
    onCloseClick: () -> Unit,
    onUpdateClick: (List<CareType>) -> Unit,
    modifier: Modifier = Modifier,
    allCareTypes: List<CareType> = CareType.entries,
) {
    var currentSelectedCareTypes by remember {
        mutableStateOf(selectedCareTypes)
    }

    CareFilterModal(
        onCloseClick = onCloseClick,
        onApplyClick = {
            onUpdateClick(currentSelectedCareTypes)
        },
        modifier = modifier,
    ) {
        val allChecked = currentSelectedCareTypes == allCareTypes

        CheckboxListItem(
            checked = allChecked,
            onClick = {
                currentSelectedCareTypes = if (allChecked) {
                    emptyList()
                } else {
                    allCareTypes
                }
            },
            text = stringResource(R.string.all_care_types)
        )

        ModalHorizontalDivider(Modifier.padding(vertical = 12.dp))

        Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
            allCareTypes.forEach { careType ->
                val checked = currentSelectedCareTypes.contains(careType)

                CheckListItem(
                    checked = checked,
                    text = careType.displayName,
                    modifier = modifier.clickable {
                        currentSelectedCareTypes = currentSelectedCareTypes.toMutableList().apply {
                            if (!checked) {
                                add(careType)
                            } else {
                                remove(careType)
                            }
                        }
                    }
                )
            }
        }
    }
}

@Preview
@Composable
private fun AllCareTypesFilterPreview() {
    CareTypeFilterModal(
        selectedCareTypes = CareType.entries,
        onCloseClick = {},
        onUpdateClick = {},
        modifier = Modifier,
    )
}

@Preview
@Composable
private fun CareTypesFilterPreview() {
    CareTypeFilterModal(
        selectedCareTypes = listOf(
            CareType.SeniorCare,
            CareType.Housekeeping
        ),
        onCloseClick = {},
        onUpdateClick = {},
        modifier = Modifier,
    )
}
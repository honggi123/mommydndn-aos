package com.mommydndn.app.ui.features.care.modal

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mommydndn.app.R
import com.mommydndn.app.domain.model.care.CareType
import com.mommydndn.app.ui.features.care.CareTypesFilter
import com.mommydndn.app.ui.features.care.displayName
import com.mommydndn.app.ui.theme.Grey200
import com.mommydndn.app.ui.theme.Grey600
import com.mommydndn.app.ui.theme.Salmon600
import com.mommydndn.app.ui.theme.White
import com.mommydndn.app.ui.theme.paragraph300

@Composable
internal fun CareTypeFilterModalBottomSheet(
    careTypesFilter: CareTypesFilter,
    onCloseClick: () -> Unit,
    onUpdateClick: (CareTypesFilter) -> Unit,
    modifier: Modifier = Modifier,
) {
    val allCareTypes = CareType.values().toList()

    val selectedCareTypes = remember {
        mutableStateOf(careTypesFilter.careTypes)
    }

    CareFilterModalBottomSheet(
        onCloseClick = onCloseClick,
        onUpdateClick = {
            // todo: if no care_type checked
            onUpdateClick(
                CareTypesFilter(
                    careTypes = selectedCareTypes.value
                )
            )
        },
        modifier = modifier,
    ) {
        AllCareTypesFilter(
            checked = selectedCareTypes == allCareTypes,
            onCheckedChange = { checked ->
                selectedCareTypes.value = if (checked) {
                    allCareTypes
                } else {
                    null
                }
            },
            modifier = Modifier,
        )

        ModalBottomSheetHorizontalDivider(
            modifier = Modifier.padding(vertical = 12.dp),
        )

        Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
            val onCheckedChange = { careType: CareType, checked: Boolean ->
                selectedCareTypes.value = selectedCareTypes.value
                    .orEmpty()
                    .toMutableList()
                    .apply {
                        if (checked) {
                            add(careType)
                        } else {
                            remove(careType)
                        }
                    }
                    .takeIf { it.isNotEmpty() }
            }

            CareType.values().forEach { careType ->
                CareTypeFilterListItem(
                    careType = careType,
                    checked = selectedCareTypes.value?.contains(careType) == true,
                    onCheckedChange = onCheckedChange,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

@Composable
private fun AllCareTypesFilter(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(color = White)
            .clickable {
                onCheckedChange(!checked)
            }
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                modifier = Modifier.size(32.dp),
                painter = painterResource(
                    id = if (checked) {
                        R.drawable.icon_checked_checkbox
                    } else {
                        R.drawable.icon_not_checked_checkbox
                    }
                ),
                contentDescription = "CareTypeCheckBox_CheckBox",
                tint = Color.Unspecified,
            )

            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(R.string.all_care_types),
                style = MaterialTheme.typography.paragraph300.merge(
                    color = Grey600,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Medium,
                )
            )
        }
    }
}

@Composable
private fun CareTypeFilterListItem(
    careType: CareType,
    checked: Boolean,
    onCheckedChange: (CareType, Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(color = White)
            .clickable {
                onCheckedChange(careType, !checked)
            }
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                modifier = Modifier.size(32.dp),
                painter = painterResource(
                    id = if (checked) {
                        R.drawable.icon_checked_mark
                    } else {
                        R.drawable.icon_not_checked_mark
                    }
                ),
                contentDescription = "CareTypeCheckBox_CheckMark",
                tint = if (checked) {
                    Salmon600
                } else {
                    Grey200
                }
            )

            Text(
                modifier = Modifier.fillMaxWidth(),
                text = careType.displayName(),
                style = MaterialTheme.typography.paragraph300.merge(
                    color = Grey600,
                    fontWeight = FontWeight.Normal,
                    fontSize = 15.sp,
                )
            )
        }
    }
}

@Preview
@Composable
private fun CareTypeFilter_AllCareTypes() {
    CareTypeFilterModalBottomSheet(
        careTypesFilter = CareTypesFilter(
            careTypes = CareType.values().toList(),
        ),
        onCloseClick = {
        },
        onUpdateClick = {
        },
        modifier = Modifier,
    )
}

@Preview
@Composable
private fun CareTypeFilter_ChildCare() {
    CareTypeFilterModalBottomSheet(
        careTypesFilter = CareTypesFilter(
            careTypes = buildList(capacity = 1) {
                add(CareType.CHILD_CARE)
            },
        ),
        onCloseClick = {
        },
        onUpdateClick = {
        },
        modifier = Modifier,
    )
}

@Preview
@Composable
private fun CareTypeFilter_SeniorCare() {
    CareTypeFilterModalBottomSheet(
        careTypesFilter = CareTypesFilter(
            careTypes = buildList(capacity = 1) {
                add(CareType.SENIOR_CARE)
            },
        ),
        onCloseClick = {
        },
        onUpdateClick = {
        },
        modifier = Modifier,
    )
}

@Preview
@Composable
private fun CareTypeFilter_CareTypes() {
    CareTypeFilterModalBottomSheet(
        careTypesFilter = CareTypesFilter(
            careTypes = buildList(capacity = 1) {
                add(CareType.SENIOR_CARE)
                add(CareType.HOUSEKEEPING)
            },
        ),
        onCloseClick = {
        },
        onUpdateClick = {
        },
        modifier = Modifier,
    )
}
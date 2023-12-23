package com.mommydndn.app.ui.features.care.provider.register.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.times
import com.mommydndn.app.R
import com.mommydndn.app.domain.model.care.OtherCondition
import com.mommydndn.app.ui.features.care.jobopening.post.component.PostFieldTitle
import com.mommydndn.app.ui.theme.Grey600
import com.mommydndn.app.ui.theme.White
import com.mommydndn.app.ui.theme.paragraph300

@Composable
internal fun RegisterCareProviderOtherConditions(
    selectedConditions: List<OtherCondition>,
    onClick: (OtherCondition) -> Unit,
    modifier: Modifier = Modifier,
    conditions: List<OtherCondition> = OtherCondition.values().toList(),
) {
    Column(
        modifier = modifier
            .background(White)
            .padding(start = 24.dp, top = 28.dp, end = 24.dp, bottom = 40.dp),
        verticalArrangement = Arrangement.spacedBy(28.dp),
    ) {
        PostFieldTitle(
            title = stringResource(R.string.other_conditions),
            subtitle = stringResource(id = R.string.optional),
            modifier = Modifier.wrapContentSize(),
        )

        val column = (conditions.size + 1) / 2

        val height = column * 32.dp + (column - 1) * 8.dp

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .fillMaxWidth()
                .height(height),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            items(conditions) { condition ->
                val checked = selectedConditions.contains(condition)

                CheckboxListItem(
                    checked = checked,
                    onClick = {
                        onClick(condition)
                    },
                    name = condition.displayName(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                )
            }
        }
    }
}

@Composable
fun CheckboxListItem(
    checked: Boolean,
    onClick: () -> Unit,
    name: String,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .clickable(onClick = onClick)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                // todo: 보이는 사이즈가 조금 다르다
                painter = painterResource(
                    id = if (checked) {
                        R.drawable.icon_checked_checkbox
                    } else {
                        R.drawable.icon_not_checked_checkbox
                    }
                ),
                contentDescription = "CheckboxListItem_Checkbox",
                modifier = Modifier.size(32.dp).run {
                    if (checked) {
                        this
                    } else {
                        padding(1.5.dp)
                    }
                },
                tint = Color.Unspecified,
            )

            Text(
                text = name,
                modifier = Modifier.weight(1F),
                style = MaterialTheme.typography.paragraph300.copy(
                    color = Grey600,
                    fontWeight = FontWeight.Medium,
                ),
            )
        }
    }
}

@Composable
fun OtherCondition.displayName() = when (this) {
    OtherCondition.CCTV -> stringResource(R.string.cctv_okay)
    OtherCondition.Occupancy -> stringResource(R.string.occupancy_available)
    OtherCondition.Pets -> stringResource(R.string.pets_okay)
    OtherCondition.Atheism -> stringResource(R.string.atheism)
    OtherCondition.NonSmoker -> stringResource(R.string.non_smoker)
}

@Preview
@Composable
private fun OtherConditions_Empty() {
    RegisterCareProviderOtherConditions(
        selectedConditions = emptyList(),
        onClick = {},
        modifier = Modifier,
    )
}

@Preview
@Composable
private fun OtherConditions_SelectedConditions() {
    RegisterCareProviderOtherConditions(
        selectedConditions = listOf(
            OtherCondition.Pets,
            OtherCondition.NonSmoker,
            OtherCondition.Atheism,
        ),
        onClick = {},
        modifier = Modifier,
    )
}
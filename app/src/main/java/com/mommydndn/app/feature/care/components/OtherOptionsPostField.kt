package com.mommydndn.app.feature.care.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
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
import com.mommydndn.app.domain.model.care.OtherOption
import com.mommydndn.app.ui.theme.Grey600
import com.mommydndn.app.ui.theme.paragraph300
import java.util.Collections

@Composable
internal fun OtherOptionsPostField(
    selectedOptions: List<OtherOption>,
    onClick: (OtherOption) -> Unit,
    modifier: Modifier = Modifier,
    title: String = stringResource(R.string.other_conditions),
    subtitle: String = stringResource(id = R.string.optional),
    options: List<OtherOption> = OtherOption.values().toList(),
) {
    PostField(
        title = title,
        subtitle = subtitle,
        modifier = modifier,
    ) {
        val column = (options.size + 1) / 2

        val height = column * 32.dp + (column - 1) * 8.dp

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .fillMaxWidth()
                .height(height),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            items(options) { condition ->
                val checked = selectedOptions.contains(condition)

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
internal fun CheckboxListItem(
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
                        R.drawable.icon_unchecked_checkbox
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
fun OtherOption.displayName() = when (this) {
    OtherOption.CCTV -> stringResource(R.string.cctv_okay)
    OtherOption.Occupancy -> stringResource(R.string.occupancy_available)
    OtherOption.Pets -> stringResource(R.string.pets_okay)
    OtherOption.Atheism -> stringResource(R.string.atheism)
    OtherOption.NonSmoker -> stringResource(R.string.non_smoker)
    OtherOption.AS -> stringResource(R.string.guarantee_as)
}

@Preview
@Composable
private fun EmptyOtherConditions() {
    OtherOptionsPostField(
        selectedOptions = emptyList(),
        onClick = {},
        modifier = Modifier,
    )
}

@Preview
@Composable
private fun NotEmptySelectedOtherConditions() {
    OtherOptionsPostField(
        selectedOptions = listOf(
            OtherOption.Pets,
            OtherOption.NonSmoker,
            OtherOption.Atheism,
        ),
        onClick = {},
        modifier = Modifier,
    )
}

@Preview
@Composable
private fun OneOtherCondition() {
    OtherOptionsPostField(
        selectedOptions = emptyList(),
        onClick = {},
        modifier = Modifier,
        options = Collections.singletonList(OtherOption.AS)
    )
}
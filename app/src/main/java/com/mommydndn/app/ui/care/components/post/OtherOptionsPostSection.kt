package com.mommydndn.app.ui.care.components.post

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
import com.mommydndn.app.domain.model.OtherOption
import com.mommydndn.app.ui.components.check.CheckboxListItem
import java.util.Collections

@Composable
fun OtherOptionsPostSection(
    selectedOptions: List<OtherOption>,
    onClick: (OtherOption) -> Unit,
    modifier: Modifier = Modifier,
    title: String = stringResource(R.string.other_conditions),
    subtitle: String = stringResource(id = R.string.optional),
    options: List<OtherOption> = OtherOption.values().toList(),
) {
    PostSection(
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
                    text = condition.displayName(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                )
            }
        }
    }
}

@Composable
fun OtherOption.displayName() = when (this) {
    OtherOption.CCTV -> stringResource(R.string.cctv_okay)
    OtherOption.Residential -> stringResource(R.string.occupancy_available)
    OtherOption.Pets -> stringResource(R.string.pets_okay)
    OtherOption.NoReligion -> stringResource(R.string.atheism)
    OtherOption.NonSmoker -> stringResource(R.string.non_smoker)
    OtherOption.AS -> stringResource(R.string.guarantee_as)
}

@Preview
@Composable
private fun EmptyOtherConditions() {
    OtherOptionsPostSection(
        selectedOptions = emptyList(),
        onClick = {},
        modifier = Modifier,
    )
}

@Preview
@Composable
private fun NotEmptySelectedOtherConditions() {
    OtherOptionsPostSection(
        selectedOptions = listOf(
            OtherOption.Pets,
            OtherOption.NonSmoker,
            OtherOption.NoReligion,
        ),
        onClick = {},
        modifier = Modifier,
    )
}

@Preview
@Composable
private fun OneOtherCondition() {
    OtherOptionsPostSection(
        selectedOptions = emptyList(),
        onClick = {},
        modifier = Modifier,
        options = Collections.singletonList(OtherOption.AS)
    )
}
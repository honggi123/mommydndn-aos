package com.mommydndn.app.ui.features.care.provider.register.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mommydndn.app.R
import com.mommydndn.app.domain.model.care.CareType
import com.mommydndn.app.ui.features.care.jobopening.post.component.ClickableCareTypeChipsRow
import com.mommydndn.app.ui.features.care.jobopening.post.component.PostFieldTitle
import com.mommydndn.app.ui.theme.White

@Composable
internal fun RegisterCareProviderCareTypes(
    selectedCareTypes: List<CareType>,
    onClick: (CareType) -> Unit,
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
            title = stringResource(R.string.wanted_care_types),
            subtitle = stringResource(id = R.string.required),
            modifier = Modifier.fillMaxWidth(),
        )

        ClickableCareTypeChipsRow(
            selectedCareTypes = selectedCareTypes,
            onCareTypeSelected = onClick,
            modifier = Modifier,
            careTypes = careTypes,
        )
    }
}

@Preview
@Composable
private fun CareTypes_NotSelected() {
    RegisterCareProviderCareTypes(
        selectedCareTypes = emptyList(),
        onClick = {},
        modifier = Modifier,
    )
}
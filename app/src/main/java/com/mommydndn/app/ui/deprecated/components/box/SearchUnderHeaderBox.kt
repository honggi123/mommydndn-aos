package com.mommydndn.app.ui.deprecated.components.box

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mommydndn.app.R
import com.mommydndn.app.data.model.common.ButtonColor
import com.mommydndn.app.data.model.common.ButtonColorType
import com.mommydndn.app.data.model.common.MinMaxRange
import com.mommydndn.app.ui.deprecated.components.button.MommyDndnButton
import com.mommydndn.app.ui.theme.Grey100
import com.mommydndn.app.ui.theme.Grey700
import com.mommydndn.app.ui.theme.MommydndnTheme
import com.mommydndn.app.ui.theme.White
import com.mommydndn.app.ui.theme.paragraph300
import com.mommydndn.app.utils.extensions.bottomBorder

@Composable
fun SearchUnderHeader(
    onSearchClick: () -> Unit,
    headerText: String,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(White)
    ) {
        Column(
            modifier = Modifier
                .padding(start = 24.dp, top = 16.dp, end = 24.dp, bottom = 16.dp)
                .bottomBorder(1.dp, Grey100)
        ) {
            MommyDndnButton(
                text = stringResource(id = R.string.search_by_current_location),
                iconResourceId = R.drawable.icon_current_location,
                color = ButtonColor.SALMON,
                colorType = ButtonColorType.WEAK,
                rangeType = MinMaxRange.MAX,
                onClick = { onSearchClick() }
            )
            Spacer(modifier = Modifier.padding(10.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    modifier = Modifier.padding(
                        top = 16.dp,
                        bottom = 16.dp,
                        start = 10.dp,
                        end = 10.dp
                    ),
                    text = headerText,
                    style = MaterialTheme.typography.paragraph300.copy(
                        fontWeight = FontWeight.Bold,
                        color = Grey700
                    )
                )
            }
        }
    }
}

@Preview
@Composable
fun previewSearchUnderHeader() {
    MommydndnTheme {
        SearchUnderHeader(headerText = "근처 동네", onSearchClick = {})
    }
}
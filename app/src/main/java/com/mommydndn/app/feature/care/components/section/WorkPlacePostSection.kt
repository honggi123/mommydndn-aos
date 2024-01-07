package com.mommydndn.app.feature.care.components.section

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mommydndn.app.R
import com.mommydndn.app.ui.theme.Grey50
import com.mommydndn.app.ui.theme.Grey500
import com.mommydndn.app.ui.theme.Grey800
import com.mommydndn.app.ui.theme.paragraph300

@Composable
internal fun WorkPlacePostSection(
    address: String,
    onAddressClick: () -> Unit,
    modifier: Modifier = Modifier,
    title: String = stringResource(R.string.work_place),
    subtitle: String = stringResource(id = R.string.required),
) {
    PostSection(
        title = title,
        subtitle = subtitle,
        modifier = modifier,
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(6.dp),
        ) {
            Text(
                text = stringResource(R.string.address),
                style = MaterialTheme.typography.paragraph300.merge(
                    color = Grey500,
                    fontWeight = FontWeight.Bold,
                )
            )

            Row(
                modifier = Modifier
                    .height(56.dp)
                    .background(color = Grey50, shape = RoundedCornerShape(12.dp))
                    .clickable(onClick = onAddressClick)
                    .padding(start = 20.dp, end = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp), // todo
                verticalAlignment = Alignment.CenterVertically,
            ) {
                // todo: when address is blank
                Text(
                    text = address,
                    modifier = Modifier.weight(1F),
                    style = MaterialTheme.typography.paragraph300.merge(
                        color = Grey800,
                        fontWeight = FontWeight.Normal,
                    )
                )

                Icon(
                    painter = painterResource(id = R.drawable.icon_arrow_down),
                    contentDescription = "WorkPlacePostField_ArrowDown",
                    modifier = Modifier,
                    tint = Color.Unspecified,
                )
            }
        }
    }
}

@Preview
@Composable
private fun WorkPlacePostField_WithAddress() {
    WorkPlacePostSection(
        address = "서울 서초구 서초중앙로 15",
        onAddressClick = {},
        modifier = Modifier,
    )
}
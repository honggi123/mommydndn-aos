package com.mommydndn.app.ui.care.list.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mommydndn.app.R
import com.mommydndn.app.ui.theme.Grey100
import com.mommydndn.app.ui.theme.Grey600
import com.mommydndn.app.ui.theme.Salmon600
import com.mommydndn.app.ui.theme.White
import com.mommydndn.app.ui.theme.caption200

@Composable
fun CareStatistics(
    matchingCount: Int,
    reviewCount: Int,
    responseRate: Int,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .border(
                1.dp,
                Grey100,
                RoundedCornerShape(6.dp)
            )
            .padding(vertical = 12.dp),
    ) {
        listOf(
            stringResource(R.string.matching) to stringResource(R.string.times, matchingCount),
            stringResource(R.string.review) to stringResource(R.string.count, reviewCount),
            stringResource(R.string.response_rate) to stringResource(R.string.percent, responseRate)
        ).forEachIndexed { index, pair ->
            Row(
                horizontalArrangement = Arrangement.spacedBy(
                    4.dp,
                    alignment = Alignment.CenterHorizontally
                ),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.weight(1F),
            ) {
                Text(
                    text = pair.first,
                    color = Grey600,
                    style = MaterialTheme.typography.caption200,
                )

                Text(
                    text = pair.second,
                    color = Salmon600,
                    style = MaterialTheme.typography.caption200,
                )
            }

            if (index < 2) {
                Divider(
                    modifier = Modifier
                        .height(20.dp)
                        .width(1.dp),
                    color = Grey100,
                    thickness = 1.dp
                )
            }
        }
    }
}

@Preview
@Composable
private fun CareStatisticsPreview() {
    CareStatistics(
        matchingCount = 24,
        reviewCount = 14,
        responseRate = 100,
        modifier = Modifier
            .background(White)
            .padding(16.dp)
    )
}
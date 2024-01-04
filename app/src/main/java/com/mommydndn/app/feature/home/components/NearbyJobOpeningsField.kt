package com.mommydndn.app.feature.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mommydndn.app.R
import com.mommydndn.app.ui.components.TagLabel
import com.mommydndn.app.ui.theme.DeepOrange
import com.mommydndn.app.ui.theme.Grey400
import com.mommydndn.app.ui.theme.Grey50
import com.mommydndn.app.ui.theme.Grey700
import com.mommydndn.app.ui.theme.Grey800
import com.mommydndn.app.ui.theme.Orange100
import com.mommydndn.app.ui.theme.caption200
import com.mommydndn.app.ui.theme.paragraph300

data class NearbyJobOpening(
    val careType: String,
    val title: String,
    val neighborhood: String,
    val pay: String,
)

@Composable
internal fun NearbyJobOpeningsField(
    nearbyJobOpenings: List<NearbyJobOpening>,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = Modifier.padding(
            start = 24.dp,
            top = 46.dp,
            end = 24.dp,
            bottom = 12.dp,
        ),
    ) {
        Text(
            text = stringResource(id = R.string.category_job_seekers_title),
            modifier = Modifier,
        )

        Text(
            text = stringResource(id = R.string.see_all),
            modifier = Modifier,
        )
    }
}

@Composable
internal fun NearbyJobOpeningItem(
    careType: String,
    title: String,
    neighborhood: String,
    pay: String,
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier) {
        Column(
            modifier = Modifier
                .background(color = Grey50, shape = RoundedCornerShape(12.dp))
                .padding(horizontal = 20.dp, vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                TagLabel(
                    text = careType,
                    textColor = DeepOrange,
                    backgroundColor = Orange100,
                    modifier = Modifier,
                )

                Text(
                    text = title,
                    color = Grey800,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    style = MaterialTheme.typography.paragraph300.merge(
                        fontWeight = FontWeight.Bold,
                    ),
                )
            }

            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Text(
                    text = neighborhood,
                    color = Grey400,
                    style = MaterialTheme.typography.caption200.merge(
                        fontWeight = FontWeight.Medium,
                    )
                )

                Text(
                    text = pay,
                    color = Grey700,
                    style = MaterialTheme.typography.paragraph300.merge(
                        fontWeight = FontWeight.Bold,
                    )
                )
            }
        }
    }
}

@Preview
@Composable
private fun HomeJobOpeningRowItem_Preview() {
    NearbyJobOpeningItem(
        careType = "육아",
        title = "2일간 풀타임으로 아이 봐주실 분 구해요",
        neighborhood = "반포동",
        pay = "시급 12,000원",
        modifier = Modifier.widthIn(max = 256.dp),
    )
}
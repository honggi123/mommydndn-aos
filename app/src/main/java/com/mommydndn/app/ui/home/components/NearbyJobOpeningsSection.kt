package com.mommydndn.app.ui.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.mommydndn.app.ui.components.tag.TagLabel
import com.mommydndn.app.ui.components.section.SectionHeader
import com.mommydndn.app.ui.theme.DeepOrange
import com.mommydndn.app.ui.theme.Grey400
import com.mommydndn.app.ui.theme.Grey50
import com.mommydndn.app.ui.theme.Grey700
import com.mommydndn.app.ui.theme.Grey800
import com.mommydndn.app.ui.theme.Orange100
import com.mommydndn.app.ui.theme.Typography
import com.mommydndn.app.ui.theme.White
import com.mommydndn.app.ui.theme.caption200
import com.mommydndn.app.ui.theme.paragraph300

data class NearbyJobOpening(
    val careType: String,
    val title: String,
    val neighborhood: String,
    val pay: String,
)

@Composable
internal fun NearbyJobOpeningsSection(
    nearbyJobOpenings: List<NearbyJobOpening>,
    onMoreClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        SectionHeader(
            title = stringResource(id = R.string.nearby_care_job_openings),
            trailingText = stringResource(id = R.string.see_more),
            onTrailingClick = onMoreClick,
        )

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(start = 32.dp, top = 28.dp, bottom = 36.dp, end = 32.dp),
        ) {
            items(nearbyJobOpenings) { nearbyJobOpening ->
                with(nearbyJobOpening) {
                    NearbyJobOpeningItem(
                        careType = careType,
                        title = title,
                        neighborhood = neighborhood,
                        pay = pay,
                        modifier = Modifier.width(256.dp), // TODO
                    )
                }
            }
        }
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
                    style = Typography.paragraph300.merge(
                        fontWeight = FontWeight.Bold,
                    ),
                )
            }

            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Text(
                    text = neighborhood,
                    color = Grey400,
                    style = Typography.caption200.merge(
                        fontWeight = FontWeight.Medium,
                    )
                )

                Text(
                    text = pay,
                    color = Grey700,
                    style = Typography.paragraph300.merge(
                        fontWeight = FontWeight.Bold,
                    )
                )
            }
        }
    }
}

@Preview
@Composable
private fun NearbyJobOpeningsSectionPreview() {
    NearbyJobOpeningsSection(
        nearbyJobOpenings = nearbyJobOpenings,
        onMoreClick = {},
        modifier = Modifier.background(White),
    )
}
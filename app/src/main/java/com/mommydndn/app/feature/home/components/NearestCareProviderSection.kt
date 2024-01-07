package com.mommydndn.app.feature.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.mommydndn.app.R
import com.mommydndn.app.ui.components.tag.TagLabel
import com.mommydndn.app.ui.components.section.SectionHeader
import com.mommydndn.app.ui.theme.DeepOrange
import com.mommydndn.app.ui.theme.Green100
import com.mommydndn.app.ui.theme.Green600
import com.mommydndn.app.ui.theme.Grey50
import com.mommydndn.app.ui.theme.Grey600
import com.mommydndn.app.ui.theme.Orange100
import com.mommydndn.app.ui.theme.Typography
import com.mommydndn.app.ui.theme.White
import com.mommydndn.app.ui.theme.paragraph300

data class NearestCareProvider(
    val profileUrl: String,
    val name: String,
    val ageRange: String,
    val gender: String,
    val careType: String,
)

@Composable
internal fun NearestCareProvidersSection(
    nearestCareProviders: List<NearestCareProvider>,
    onAllClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        SectionHeader(
            title = stringResource(id = R.string.nearest_care_providers),
            trailingText = stringResource(id = R.string.see_all),
            onTrailingClick = onAllClick,
        )

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(32.dp),
            contentPadding = PaddingValues(start = 32.dp, top = 28.dp, bottom = 36.dp, end = 32.dp),
        ) {
            items(nearestCareProviders) { nearestCareProvider ->
                with(nearestCareProvider) {
                    NearestCareProviderItem(
                        profileUrl = profileUrl,
                        name = name,
                        age = ageRange,
                        gender = gender,
                        careType = careType,
                        modifier = Modifier,
                    )
                }
            }
        }
    }
}

@Composable
internal fun NearestCareProviderItem(
    profileUrl: String,
    name: String,
    age: String,
    gender: String,
    careType: String,
    modifier: Modifier = Modifier,
) {
    val profileImagePainter = rememberAsyncImagePainter(
        profileUrl,
        contentScale = ContentScale.Crop,
    )

    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier,
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                painter = profileImagePainter,
                contentDescription = "CareProviderProfile_Image",
                modifier = Modifier
                    .size(108.dp)
                    .clip(CircleShape)
                    .background(Grey50, CircleShape),
                contentScale = ContentScale.Crop,
            )

            Text(
                text = name,
                modifier = Modifier,
                color = Grey600,
                textAlign = TextAlign.Center,
                overflow = TextOverflow.Ellipsis,
                style = Typography.paragraph300.merge(
                    fontWeight = FontWeight.Medium,
                )
            )

            Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                TagLabel(
                    text = "$age $gender",
                    textColor = Green600,
                    backgroundColor = Green100,
                )

                TagLabel(
                    text = careType,
                    textColor = DeepOrange,
                    backgroundColor = Orange100,
                )
            }
        }
    }
}

@Preview
@Composable
private fun NearestCareProvidersSectionPreview() {
    NearestCareProvidersSection(
        nearestCareProviders = nearestCareProviders,
        onAllClick = {},
        modifier = Modifier.background(White),
    )
}

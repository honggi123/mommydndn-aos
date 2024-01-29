package com.mommydndn.app.ui.care.list.agency

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.mommydndn.app.R
import com.mommydndn.app.domain.model.CareType
import com.mommydndn.app.ui.care.list.components.AvailableNeighborhood
import com.mommydndn.app.ui.care.list.components.CareStatistics
import com.mommydndn.app.ui.care.list.components.DndnScore
import com.mommydndn.app.ui.care.list.components.displayName
import com.mommydndn.app.ui.components.tag.TagLabel
import com.mommydndn.app.ui.theme.Blue50
import com.mommydndn.app.ui.theme.Blue600
import com.mommydndn.app.ui.theme.DeepOrange
import com.mommydndn.app.ui.theme.Grey50
import com.mommydndn.app.ui.theme.Grey800
import com.mommydndn.app.ui.theme.Orange100
import com.mommydndn.app.ui.theme.White
import com.mommydndn.app.ui.theme.paragraph300

data class CareAgencyUiModel(
    val dndnCertified: Boolean,
    val name: String,
    val neighborhood: String,
    val dndnScore: Float,
    val careTypes: Set<CareType>,
    val profileImageUrl: String,
    val isLiked: Boolean,
    val matchingCount: Int,
    val reviewCount: Int,
    val responseRate: Int,
)

@Composable
internal fun CareAgencyList(
    careAgencies: List<CareAgencyUiModel>,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(vertical = 16.dp, horizontal = 24.dp),
        verticalArrangement = Arrangement.spacedBy(32.dp),
    ) {
        items(careAgencies) { careAgency ->
            with(careAgency) {
                CareAgencyListItem(
                    dndnCertified = dndnCertified,
                    name = name,
                    neighborhood = neighborhood,
                    dndnScore = dndnScore,
                    careTypes = careTypes,
                    profileImageUrl = profileImageUrl,
                    isLiked = isLiked,
                    onLikeClick = {},
                    matchingCount = matchingCount,
                    reviewCount = reviewCount,
                    responseRate = responseRate,
                    modifier = Modifier,
                )
            }
        }
    }
}

@Composable
internal fun CareAgencyListItem(
    dndnCertified: Boolean,
    name: String,
    neighborhood: String,
    dndnScore: Float,
    careTypes: Set<CareType>,
    profileImageUrl: String,
    isLiked: Boolean,
    onLikeClick: () -> Unit,
    matchingCount: Int,
    reviewCount: Int,
    responseRate: Int,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.padding(6.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Row {
            Column(
                modifier = Modifier.weight(1F),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                if (dndnCertified) {
                    CertifiedAgency()
                }

                Text(
                    text = name,
                    color = Grey800,
                    style = MaterialTheme.typography.paragraph300.merge(
                        fontWeight = FontWeight.Bold
                    )
                )

                AvailableNeighborhood(neighborhood = neighborhood)

                DndnScore(score = dndnScore)

                Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                    careTypes.forEach { careType ->
                        TagLabel(
                            text = careType.displayName,
                            textColor = DeepOrange,
                            backgroundColor = Orange100,
                        )
                    }
                }
            }

            Box {
                AsyncImage(
                    model = profileImageUrl,
                    contentDescription = null,
                    modifier = Modifier
                        .width(130.dp)
                        .height(124.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(Grey50)
                )

                Image(
                    painter = painterResource(
                        id = if (isLiked) {
                            R.drawable.icon_heart_fill_salmon
                        } else {
                            R.drawable.icon_heart
                        }
                    ),
                    contentDescription = "좋아요",
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .size(36.dp)
                        .clickable(onClick = onLikeClick),
                )
            }
        }

        CareStatistics(
            matchingCount = matchingCount,
            reviewCount = reviewCount,
            responseRate = responseRate,
            modifier = Modifier,
        )
    }
}

@Composable
internal fun CertifiedAgency(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = R.drawable.icon_certificate),
            contentDescription = null,
            modifier = Modifier.size(24.dp),
            tint = Color.Unspecified,
        )

        TagLabel(
            text = stringResource(R.string.trustworthy_agency_cetification),
            textColor = Blue600,
            backgroundColor = Blue50,
        )
    }
}

@Preview
@Composable
private fun CareAgencyListItemPreview() {
    CareAgencyListItem(
        dndnCertified = true,
        name = "피카부 베이비시터",
        neighborhood = "서초동 외 9개 동네",
        dndnScore = 5.0F,
        careTypes = CareType.entries.toSet(),
        profileImageUrl = "http://www.bing.com/search?q=leo",
        isLiked = true,
        onLikeClick = {},
        matchingCount = 821,
        reviewCount = 624,
        responseRate = 100,
        modifier = Modifier
            .background(White)
            .padding(horizontal = 24.dp, vertical = 12.dp),
    )
}
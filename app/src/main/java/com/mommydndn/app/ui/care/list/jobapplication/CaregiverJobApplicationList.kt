package com.mommydndn.app.ui.care.list.jobapplication

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mommydndn.app.R
import com.mommydndn.app.domain.model.CareType
import com.mommydndn.app.ui.care.list.components.AvailableNeighborhood
import com.mommydndn.app.ui.care.list.components.CareStatistics
import com.mommydndn.app.ui.care.list.components.DndnScore
import com.mommydndn.app.ui.care.list.components.ProfileImage
import com.mommydndn.app.ui.care.list.components.displayName
import com.mommydndn.app.ui.components.tag.TagLabel
import com.mommydndn.app.ui.theme.DeepOrange
import com.mommydndn.app.ui.theme.Green100
import com.mommydndn.app.ui.theme.Green600
import com.mommydndn.app.ui.theme.Grey800
import com.mommydndn.app.ui.theme.Orange100
import com.mommydndn.app.ui.theme.White
import com.mommydndn.app.ui.theme.paragraph300

data class CaregiverJobApplicationUiModel(
    val profileImageUrl: String,
    val dndnCertified: Boolean,
    val nickname: String,
    val neighborhood: String,
    val dndnScore: Float,
    val isLiked: Boolean,
    val ageRangeAndGender: String,
    val careTypes: Set<CareType>,
    val matchingCount: Int,
    val reviewCount: Int,
    val responseRate: Int,
)

@Composable
fun CaregiverJobApplicationList(
    jobApplications: List<CaregiverJobApplicationUiModel>,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(vertical = 16.dp, horizontal = 24.dp),
        verticalArrangement = Arrangement.spacedBy(32.dp),
    ) {
        items(jobApplications) { careWorker ->
            with(careWorker) {
                CaregiverJobApplicationListItem(
                    profileImageUrl = profileImageUrl,
                    dndnCertified = dndnCertified,
                    nickname = nickname,
                    neighborhood = neighborhood,
                    dndnScore = dndnScore,
                    isLiked = isLiked,
                    onLikeClick = {},
                    ageRangeAndGender = ageRangeAndGender,
                    careTypes = careTypes,
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
internal fun CaregiverJobApplicationListItem(
    profileImageUrl: String,
    dndnCertified: Boolean,
    nickname: String,
    neighborhood: String,
    dndnScore: Float,
    isLiked: Boolean,
    onLikeClick: () -> Unit,
    ageRangeAndGender: String,
    careTypes: Set<CareType>,
    matchingCount: Int,
    reviewCount: Int,
    responseRate: Int,
    modifier: Modifier = Modifier,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(10.dp),
        modifier = modifier.padding(6.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(20.dp),
            modifier = Modifier.fillMaxWidth(),
        ) {
            ProfileImage(
                url = profileImageUrl,
                dndnCertified = dndnCertified,
                modifier = Modifier,
            )

            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp),
                modifier = Modifier.weight(1F),
            ) {
                Text(
                    text = nickname,
                    color = Grey800,
                    style = MaterialTheme.typography.paragraph300.merge(
                        fontWeight = FontWeight.Bold
                    )
                )

                AvailableNeighborhood(neighborhood = neighborhood)

                DndnScore(score = dndnScore)
            }

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
                    .size(36.dp)
                    .clickable(onClick = onLikeClick),
            )
        }

        Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
            TagLabel(
                text = ageRangeAndGender,
                textColor = Green600,
                backgroundColor = Green100,
            )

            // TODO: 순서
            careTypes.forEach { careType ->
                TagLabel(
                    text = careType.displayName,
                    textColor = DeepOrange,
                    backgroundColor = Orange100,
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

@Preview
@Composable
private fun CaregiverJobApplicationListItemPreview() {
    CaregiverJobApplicationListItem(
        profileImageUrl = "http://www.bing.com/search?q=menandri",
        dndnCertified = true,
        nickname = "세아쌤",
        neighborhood = "반포동 외 24개 동네",
        dndnScore = 5.0F,
        isLiked = true,
        onLikeClick = {},
        ageRangeAndGender = "30대 여성",
        careTypes = setOf(
            CareType.ChildCare,
            CareType.Housekeeping,
        ),
        matchingCount = 24,
        reviewCount = 14,
        responseRate = 100,
        modifier = Modifier
            .background(White)
            .padding(24.dp),
    )
}
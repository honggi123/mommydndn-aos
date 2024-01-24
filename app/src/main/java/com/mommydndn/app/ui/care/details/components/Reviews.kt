package com.mommydndn.app.ui.care.details.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.mommydndn.app.R
import com.mommydndn.app.domain.model.CareType
import com.mommydndn.app.ui.care.list.components.displayName
import com.mommydndn.app.ui.components.tag.TagLabel
import com.mommydndn.app.ui.theme.DeepOrange
import com.mommydndn.app.ui.theme.Grey400
import com.mommydndn.app.ui.theme.Grey50
import com.mommydndn.app.ui.theme.Grey600
import com.mommydndn.app.ui.theme.Orange100
import com.mommydndn.app.ui.theme.caption100
import com.mommydndn.app.ui.theme.caption200
import java.time.LocalDate

data class DetailsReviewUiModel(
    val nickname: String,
    val reviewedAt: LocalDate,
    val dndnScore: Float,
    val careTypes: List<CareType>,
    val content: String,
)

@Composable
internal fun DetailsReviews(
    name: String,
    reviews: List<DetailsReviewUiModel>,
    onViewAllClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier) {
        Column(verticalArrangement = Arrangement.spacedBy(36.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom,
            ) {
                CareDetailsSectionTitle(title = stringResource(R.string.reviews_of, name))

                Text(
                    text = stringResource(id = R.string.view_all),
                    modifier = Modifier.clickable(onClick = onViewAllClick),
                    color = Grey400,
                    style = MaterialTheme.typography.caption200
                )
            }

            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                reviews.forEach { review ->
                    with(review) {
                        DetailsReview(
                            nickname = nickname,
                            reviewedAt = reviewedAt,
                            dndnScore = dndnScore,
                            careTypes = careTypes,
                            content = review.content,
                        )
                    }
                }
            }
        }
    }
}

@Composable
internal fun DetailsReview(
    nickname: String,
    reviewedAt: LocalDate,
    dndnScore: Float,
    careTypes: List<CareType>,
    content: String,
) {
    Column(
        modifier = Modifier
            .background(Grey50, RoundedCornerShape(6.dp))
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(6.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = nickname,
                color = Grey600,
                style = MaterialTheme.typography.caption200.merge(
                    fontWeight = FontWeight.Bold
                ),
            )

            Text(
                text = with(reviewedAt) {
                    stringResource(R.string.year_month, year, monthValue)
                },
                color = Grey400,
                style = MaterialTheme.typography.caption100,
            )
        }

        // TODO: 스코어 표기, 스코어 없을 때
        Row(
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                painter = painterResource(id = R.drawable.icon_muscle),
                contentDescription = null,
                modifier = Modifier.size(16.dp),
                tint = Color.Unspecified,
            )

            Text(
                text = dndnScore.toString(),
                color = Grey600,
                style = MaterialTheme.typography.caption200,
            )

            careTypes.forEach { careType ->
                TagLabel(
                    text = careType.displayName,
                    textColor = DeepOrange,
                    backgroundColor = Orange100,
                )
            }
        }

        Text(
            text = content,
            modifier = Modifier.fillMaxWidth(),
            color = Grey600,
            style = MaterialTheme.typography.caption200,
        )
    }
}
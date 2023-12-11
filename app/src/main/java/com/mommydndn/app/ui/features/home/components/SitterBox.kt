package com.mommydndn.app.ui.features.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.mommydndn.app.data.model.care.CaringType
import com.mommydndn.app.data.model.common.BadgeColorType
import com.mommydndn.app.domain.model.care.JobSeeker
import com.mommydndn.app.ui.components.common.Badge
import com.mommydndn.app.ui.theme.Grey600
import com.mommydndn.app.ui.theme.caption200

@Composable
fun SitterBox(
    item: JobSeeker,
    modifier: Modifier = Modifier
) {
    val profilePainter = rememberImagePainter(
        data = item.profileUrl,
        builder = {
            crossfade(true)
        }
    )

    Box(
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Image(
                painter = profilePainter,
                contentDescription = null,
                modifier = Modifier
                    .size(108.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )

            Text(
                modifier = Modifier.fillMaxWidth(),
                overflow = TextOverflow.Ellipsis,
                text = item.nickname,
                style = MaterialTheme.typography.caption200.copy(
                    fontWeight = FontWeight.Medium,
                    color = Grey600,
                    textAlign = TextAlign.Center
                )
            )

            Row(modifier = Modifier.fillMaxWidth()) {
                Badge(colorType = BadgeColorType.GREEN, text = item.ageAndGender)
                Spacer(modifier = Modifier.padding(6.dp))
                Badge(colorType = BadgeColorType.ORANGE, text = item.caringType.value)
            }
        }
    }
}

@Preview
@Composable
fun SitterBoxPreview() {
    val mockJobSeeker = JobSeeker(
        nickname = "김홍기",
        ageAndGender = "20대 남자",
        caringType = CaringType.PARENTING,
        jobSeekerId = 123,
        profileUrl = ""
    )

    SitterBox(
        mockJobSeeker,
        modifier = Modifier.width(116.dp)
    )
}

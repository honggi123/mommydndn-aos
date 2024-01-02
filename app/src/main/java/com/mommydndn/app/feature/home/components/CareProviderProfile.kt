package com.mommydndn.app.feature.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
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
import com.mommydndn.app.domain.model.care.JobSeeker
import com.mommydndn.app.feature.components.TagLabel
import com.mommydndn.app.ui.theme.DeepOrange
import com.mommydndn.app.ui.theme.Green100
import com.mommydndn.app.ui.theme.Green600
import com.mommydndn.app.ui.theme.Grey600
import com.mommydndn.app.ui.theme.Orange100
import com.mommydndn.app.ui.theme.caption200

@Composable
internal fun CareProviderProfile(
    profileUrl: String,
    name: String,
    age: String,
    gender: String,
    careType: String,
    modifier: Modifier = Modifier,
) {
    val profileImagePainter = rememberImagePainter(data = profileUrl) {
        crossfade(true)
    }

    Box(
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Image(
                painter = profileImagePainter,
                contentDescription = null,
                modifier = Modifier
                    .size(108.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )

            Text(
                modifier = Modifier.fillMaxWidth(),
                overflow = TextOverflow.Ellipsis,
                text = name,
                style = MaterialTheme.typography.caption200.copy(
                    fontWeight = FontWeight.Medium,
                    color = Grey600,
                    textAlign = TextAlign.Center
                )
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                horizontalArrangement = Arrangement.spacedBy(6.dp),
            ) {
                TagLabel(
                    text = "$age $gender",
                    textColor = Green600,
                    backgroundColor = Green100,
                    modifier = Modifier,
                )

                TagLabel(
                    text = careType,
                    textColor = DeepOrange,
                    backgroundColor = Orange100,
                    modifier = Modifier,
                )
            }
        }
    }
}

@Preview
@Composable
private fun CareProviderProfile_Preview() {
    val mockJobSeeker = JobSeeker(
        nickname = "김홍기",
        ageAndGender = "20대 남자",
        caringType = CaringType.PARENTING,
        jobSeekerId = 123,
        profileUrl = ""
    )

    /*
    CareProviderProfile(
        mockJobSeeker,
        modifier = Modifier.width(116.dp)
    )
     */
}

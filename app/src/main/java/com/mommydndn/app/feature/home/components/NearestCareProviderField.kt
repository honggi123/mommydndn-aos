package com.mommydndn.app.feature.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
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
import coil.compose.rememberAsyncImagePainter
import com.mommydndn.app.domain.model.care.CareType
import com.mommydndn.app.feature.care.screen.displayName
import com.mommydndn.app.ui.components.TagLabel
import com.mommydndn.app.ui.theme.DeepOrange
import com.mommydndn.app.ui.theme.Green100
import com.mommydndn.app.ui.theme.Green600
import com.mommydndn.app.ui.theme.Grey600
import com.mommydndn.app.ui.theme.Orange100
import com.mommydndn.app.ui.theme.White
import com.mommydndn.app.ui.theme.caption200

data class NearestCareProvider(
    val profileUrl: String,
    val name: String,
    val ageRange: String,
    val gender: String,
    val careType: String,
)

@Composable
internal fun NearestCareProvidersField() {
    
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
                    .clip(CircleShape),
                contentScale = ContentScale.Crop,
            )

            Text(
                text = name,
                modifier = Modifier,
                color = Grey600,
                textAlign = TextAlign.Center,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.caption200.merge(
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
private fun CareProviderProfile_Preview() {
    NearestCareProviderItem(
        profileUrl = "",
        name = "김홍기",
        age = "20대",
        gender = "남자",
        careType = CareType.SENIOR_CARE.displayName(),
        modifier = Modifier.background(White),
    )
}

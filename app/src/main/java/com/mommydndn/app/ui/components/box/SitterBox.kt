package com.mommydndn.app.ui.components.box

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.mommydndn.app.data.model.common.BadgeColorType
import com.mommydndn.app.data.model.care.JobSeeker
import com.mommydndn.app.ui.components.common.Badge
import com.mommydndn.app.ui.theme.Grey600
import com.mommydndn.app.ui.theme.caption200

@Composable
fun SitterBox(
    item: JobSeeker
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
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = profilePainter,
                contentDescription = null,
                modifier = Modifier
                    .size(108.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.padding(8.dp))
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = item.nickname,
                style = MaterialTheme.typography.caption200.copy(
                    fontWeight = FontWeight.Medium,
                    color = Grey600,
                    textAlign = TextAlign.Center
                )
            )
            Spacer(modifier = Modifier.padding(8.dp))

            Row(modifier = Modifier.fillMaxWidth()) {
                Badge(colorType = BadgeColorType.GREEN, text = item.ageAndGender)
                Spacer(modifier = Modifier.padding(6.dp))
                Badge(colorType = BadgeColorType.ORANGE, text = item.caringType.value)
            }
        }
    }
}

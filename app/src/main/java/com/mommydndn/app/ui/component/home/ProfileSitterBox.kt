package com.mommydndn.app.ui.component.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.mommydndn.app.data.model.BannerColorType
import com.mommydndn.app.ui.theme.Grey600
import com.mommydndn.app.ui.theme.MommydndnaosTheme
import com.mommydndn.app.ui.theme.caption200

@Composable
fun ProfileSitterBox(
    profileImgUrl: String = "",
    nameText: String = "",
    ageAndGenderText: String = "",
    caringTypeText: String = ""
) {
    val profilePainter = rememberImagePainter(
        data = profileImgUrl,
        builder = {
            crossfade(true)
        }
    )

    Box(
        modifier = Modifier.width(116.dp),
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
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.padding(8.dp))
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = nameText,
                style = MaterialTheme.typography.caption200.copy(
                    fontWeight = FontWeight.Medium,
                    color = Grey600,
                    platformStyle = PlatformTextStyle(
                        includeFontPadding = false
                    ),
                    textAlign = TextAlign.Center
                )
            )
            Spacer(modifier = Modifier.padding(8.dp))

            Row(modifier = Modifier.fillMaxWidth()) {
                Badge(colorType = BannerColorType.GREEN, text = ageAndGenderText)
                Spacer(modifier = Modifier.padding(6.dp))
                Badge(colorType = BannerColorType.ORANGE, text = caringTypeText)
            }
        }

    }
}

@Preview
@Composable
fun previewProfileSitterBox() {
    MommydndnaosTheme {
        ProfileSitterBox(
            profileImgUrl = "",
            nameText = "text",
            ageAndGenderText = "20대 여성",
            caringTypeText = "육아"
        )
    }
}
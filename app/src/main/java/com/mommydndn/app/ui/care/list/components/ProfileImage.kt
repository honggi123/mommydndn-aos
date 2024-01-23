package com.mommydndn.app.ui.care.list.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.mommydndn.app.R
import com.mommydndn.app.ui.theme.Grey50
import com.mommydndn.app.ui.theme.White

@Composable
internal fun ProfileImage(
    url: String,
    dndnCertified: Boolean,
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier) {
        AsyncImage(
            model = url,
            contentDescription = "프로필 사진",
            modifier = Modifier
                .size(72.dp)
                .clip(CircleShape)
                .background(Grey50),
            contentScale = ContentScale.Crop
        )

        if (dndnCertified) {
            Icon(
                painter = painterResource(id = R.drawable.icon_certificate),
                contentDescription = "든든 인증",
                modifier = Modifier
                    .align(alignment = Alignment.BottomEnd)
                    .size(24.dp)
                    .clip(CircleShape)
                    .background(White),
                tint = Color.Unspecified,
            )
        }
    }
}
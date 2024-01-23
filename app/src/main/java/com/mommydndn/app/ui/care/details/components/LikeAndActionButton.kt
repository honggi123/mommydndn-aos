package com.mommydndn.app.ui.care.details.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.mommydndn.app.R
import com.mommydndn.app.ui.theme.Grey100
import com.mommydndn.app.ui.theme.Salmon600
import com.mommydndn.app.ui.theme.White
import com.mommydndn.app.ui.theme.paragraph400

@Composable
internal fun LikeAndActionButton(
    isLiked: Boolean,
    onLikeClick: () -> Unit,
    actionName: String,
    onActionClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier,) {
        Divider(color = Grey100, thickness = 1.dp)

        Row(
            modifier = Modifier.padding(horizontal = 24.dp, vertical = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
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

            Text(
                text = actionName,
                modifier = Modifier
                    .weight(1F)
                    .clip(RoundedCornerShape(12.dp))
                    .background(Salmon600)
                    .clickable(onClick = onActionClick)
                    .padding(vertical = 16.dp),
                color = White,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.paragraph400.merge(
                    fontWeight = FontWeight.Medium
                )
            )
        }
    }
}
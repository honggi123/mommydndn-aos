package com.mommydndn.app.ui.care.details.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.mommydndn.app.R
import com.mommydndn.app.ui.theme.Grey600
import com.mommydndn.app.ui.theme.Grey700
import com.mommydndn.app.ui.theme.caption100
import com.mommydndn.app.ui.theme.caption200

@Composable
internal fun CareDetailsBio(
    name: String,
    bio: String,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {
        Column(
            modifier = Modifier
                .background(
                    // TODO
                    Brush.horizontalGradient(
                        colors = listOf(
                            Color(0xFFF0DEDC),
                            Color(0xFFFAE8E4),
                            Color(0xFFFAEAE2),
                            Color(0xFFFCEFDB),
                        )
                    ),
                    RoundedCornerShape(12.dp)
                )
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(horizontal = 24.dp, vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(R.string.profile_bio, name),
                color = Grey700,
                style = MaterialTheme.typography.caption200.merge(
                    fontWeight = FontWeight.Medium
                )
            )

            Text(
                text = bio,
                color = Grey600,
                style = MaterialTheme.typography.caption100
            )
        }
    }
}
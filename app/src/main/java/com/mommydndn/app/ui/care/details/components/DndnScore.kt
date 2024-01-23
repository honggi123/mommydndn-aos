package com.mommydndn.app.ui.care.details.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
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
import com.mommydndn.app.ui.theme.Grey600
import com.mommydndn.app.ui.theme.caption100
import com.mommydndn.app.ui.theme.paragraph400

@Composable
internal fun CareDetailsDndnScore(score: Float) {
    Column(horizontalAlignment = Alignment.End) {
        Text(
            text = score.toString(), // TODO
            color = Color(0xFFF28005),
            style = MaterialTheme.typography.paragraph400.merge(
                fontWeight = FontWeight.Bold
            ),
        )

        Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
            Icon(
                painter = painterResource(id = R.drawable.icon_muscle),
                contentDescription = null,
                modifier = Modifier.size(16.dp),
                tint = Color.Unspecified,
            )

            Text(
                text = stringResource(R.string.dndn_score),
                color = Grey600,
                style = MaterialTheme.typography.caption100.merge(
                    fontWeight = FontWeight.Medium
                )
            )
        }
    }
}
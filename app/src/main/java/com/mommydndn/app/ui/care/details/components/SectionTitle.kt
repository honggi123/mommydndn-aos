package com.mommydndn.app.ui.care.details.components

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.mommydndn.app.ui.theme.Grey700
import com.mommydndn.app.ui.theme.paragraph300

@Composable
internal fun DetailsSectionTitle(
    title: String,
    modifier: Modifier = Modifier,
) {
    Text(
        text = title,
        modifier = modifier,
        color = Grey700,
        style = MaterialTheme.typography.paragraph300.merge(
            fontWeight = FontWeight.Bold
        )
    )
}
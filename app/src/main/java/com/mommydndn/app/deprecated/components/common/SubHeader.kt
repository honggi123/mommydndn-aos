package com.mommydndn.app.deprecated.components.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mommydndn.app.ui.theme.Grey400
import com.mommydndn.app.ui.theme.GreyOpacity700
import com.mommydndn.app.ui.theme.White
import com.mommydndn.app.ui.theme.paragraph300

@Composable
fun SubHeader(
    modifier: Modifier = Modifier,
    text: String = ""
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(color = GreyOpacity700),
        contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = Modifier.padding(top = 12.dp, bottom = 12.dp),
            text = text,
            style = MaterialTheme.typography.paragraph300.copy(
                fontWeight = FontWeight.Normal,
            ),
            color = White
        )
    }
}


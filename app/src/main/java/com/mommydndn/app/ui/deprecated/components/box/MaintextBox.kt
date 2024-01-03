package com.mommydndn.app.ui.deprecated.components.box

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mommydndn.app.ui.theme.Grey500
import com.mommydndn.app.ui.theme.Grey700
import com.mommydndn.app.ui.theme.AppTheme
import com.mommydndn.app.ui.theme.heading800
import com.mommydndn.app.ui.theme.paragraph300

@Composable
fun MaintextBox(
    modifier: Modifier = Modifier,
    captionText: String,
    titleText: String
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(start = 32.dp, top = 16.dp, end = 32.dp, bottom = 40.dp)
    ) {
        Column {
            Text(
                text = captionText,
                style = MaterialTheme.typography.paragraph300.copy(
                    fontWeight = FontWeight.Normal,
                    color = Grey500
                )
            )
            Spacer(modifier = Modifier.padding(6.dp))
            Text(
                text = titleText,
                style = MaterialTheme.typography.heading800.copy(
                    fontWeight = FontWeight.Bold,
                    color = Grey700
                )
            )
        }
    }
}

@Preview
@Composable
fun previewMaintextBox() {
    AppTheme {
        MaintextBox(
            captionText = "caption",
            titleText = "title"
        )
    }
}
package com.mommydndn.app.ui.components.button

import androidx.annotation.StringRes
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mommydndn.app.ui.theme.*

@Composable
fun SectionButton(
    modifier: Modifier = Modifier,
    @StringRes id: Int? = null,
    text: String = "",
    onClick: () -> Unit
) {
    Button(
        modifier = modifier
            .width(390.dp)
            .wrapContentHeight()
            .border(width = 1.dp, color = Grey100),
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = White,
            contentColor = Salmon600,
            disabledContentColor = Salmon600,
            disabledBackgroundColor = White
        ),
        contentPadding = PaddingValues(20.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = id?.let { stringResource(id = id) } ?: text,
                style = MaterialTheme.typography.paragraph300.copy(
                    fontWeight = FontWeight.Normal
                )
            )
        }
    }
}

@Preview
@Composable
fun SectionButtonPreview() {
    MommydndnaosTheme {
        CtaButton(
            text = "Section"
        ) {}
    }
}
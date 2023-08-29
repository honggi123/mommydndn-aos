package com.mommydndn.app.ui.component

import androidx.annotation.StringRes
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
fun CtaButton(
    modifier: Modifier = Modifier,
    @StringRes id: Int? = null,
    text: String = "",
    onClick: () -> Unit
) {
    Button(
        modifier = modifier
            .width(342.dp)
            .wrapContentHeight(),
        shape = MaterialTheme.shapes.large,
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Salmon600,
            contentColor = White,
            disabledContentColor = Grey300,
            disabledBackgroundColor = Grey100
        ),
        contentPadding = PaddingValues(Paddings.xlarge)
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = id?.let { stringResource(id = id) } ?: text,
                style = MaterialTheme.typography.paragraph500.copy(
                    fontWeight = FontWeight.Normal
                )
            )
        }
    }
}

@Preview
@Composable
fun CtaButtonPreview() {
    MommydndnaosTheme {
        CtaButton(
            text = "CTA"
        ) {}
    }
}
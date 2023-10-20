package com.mommydndn.app.ui.components.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mommydndn.app.R
import com.mommydndn.app.ui.theme.Grey400
import com.mommydndn.app.ui.theme.Grey600
import com.mommydndn.app.ui.theme.MommydndnaosTheme
import com.mommydndn.app.ui.theme.White
import com.mommydndn.app.ui.theme.caption200
import com.mommydndn.app.ui.theme.paragraph300

@Composable
fun SubBanner(
    modifier: Modifier = Modifier
) {

    val text = buildAnnotatedString {
        withStyle(
            style = SpanStyle(
                color = Grey400
            )
        ) {
            append("지금까지의 마미든든에 대한")
        }
        append("\n")
        withStyle(
            style = SpanStyle(
                color = Grey600
            )
        ) {
            append("소중한 의견을 남겨주세요")
        }
    }


    Box(
        modifier = modifier
            .background(color = White)
            .padding(start = 32.dp, end = 32.dp, top = 56.dp, bottom = 56.dp)
    ) {
        Row {
            Image(
                painter = painterResource(id = R.drawable.ic_heartletter),
                contentDescription = null
            )
            Spacer(modifier = Modifier.padding(24.dp))
            Column {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = text,
                    style = MaterialTheme.typography.caption200.copy(
                        fontWeight = FontWeight.Bold
                    )
                )
                Spacer(modifier = Modifier.padding(14.dp))
                Button(onClick = { }) {
                    Text(
                        text = "의견 남기기",
                        style = MaterialTheme.typography.paragraph300.copy(
                            fontWeight = FontWeight.Medium,
                            color = Grey600
                        )
                    )
                }
            }
        }

    }
}

@Preview
@Composable
fun previewSubBanner() {
    MommydndnaosTheme {
        SubBanner()
    }
}
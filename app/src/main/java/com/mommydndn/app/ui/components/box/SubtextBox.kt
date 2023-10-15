package com.mommydndn.app.ui.components.box

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mommydndn.app.ui.theme.Grey400
import com.mommydndn.app.ui.theme.Grey800
import com.mommydndn.app.ui.theme.MommydndnaosTheme
import com.mommydndn.app.ui.theme.caption200
import com.mommydndn.app.ui.theme.heading600
import com.mommydndn.app.ui.theme.paragraph300

enum class SubtextBoxSize { L, M, S }

@Composable
fun SubtextBox(
    modifier: Modifier = Modifier,
    size: SubtextBoxSize = SubtextBoxSize.M,
    titleText: String = "",
    subtitleText: String = "",
    rightButtonText: String = "",
    rightButtonOnClick: () -> Unit = {}
) {

    val titleTextStyle = MaterialTheme.typography.heading600.copy(
        fontWeight = FontWeight.Bold,
        color = Grey800,
        platformStyle = PlatformTextStyle(includeFontPadding = false)
    )

    val subtitleTextStyle = MaterialTheme.typography.caption200.copy(
        fontWeight = FontWeight.Normal,
        color = Grey400,
        platformStyle = PlatformTextStyle(includeFontPadding = false)
    )

    val rightTextStyle = when (size) {
        SubtextBoxSize.L -> MaterialTheme.typography.paragraph300.copy(fontWeight = FontWeight.Medium)
        SubtextBoxSize.S, SubtextBoxSize.M -> MaterialTheme.typography.caption200
    }.copy(
        color = Grey400,
        platformStyle = PlatformTextStyle(includeFontPadding = false)
    )

    val padding = when (size) {
        SubtextBoxSize.S -> PaddingValues(start = 24.dp, top = 28.dp, end = 24.dp, bottom = 12.dp)
        SubtextBoxSize.M -> PaddingValues(start = 24.dp, top = 40.dp, end = 24.dp, bottom = 12.dp)
        SubtextBoxSize.L -> PaddingValues(start = 24.dp, top = 46.dp, end = 24.dp, bottom = 12.dp)
    }

    Box(
        modifier = modifier
            .wrapContentHeight(),
    ) {
        Row(
            modifier = Modifier.padding(padding),
            verticalAlignment = Alignment.Bottom
        ) {
            Text(
                text = titleText,
                style = titleTextStyle
            )

            Spacer(modifier = Modifier.padding(4.dp))
            Text(
                text = subtitleText,
                style = subtitleTextStyle
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = rightButtonText,
                style = rightTextStyle,
                modifier = Modifier.clickable {
                    rightButtonOnClick()
                }
            )
        }
    }
}

@Preview
@Composable
fun previewSubtextBox() {
    MommydndnaosTheme {
        SubtextBox(
            size = SubtextBoxSize.L,
            titleText = "title",
            subtitleText = "sub",
            rightButtonText = "right"
        )
    }
}
package com.mommydndn.app.ui.components.box

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mommydndn.app.ui.theme.Grey200
import com.mommydndn.app.ui.theme.Grey400
import com.mommydndn.app.ui.theme.Grey800
import com.mommydndn.app.ui.theme.White
import com.mommydndn.app.ui.theme.caption200
import com.mommydndn.app.ui.theme.heading600
import com.mommydndn.app.ui.theme.paragraph300

// todo
enum class SubtextBoxSize { L, M, S }

// todo: rename
@Composable
fun SubtextBox(
    size: SubtextBoxSize,
    title: String,
    subtitle: String,
    modifier: Modifier = Modifier,
    trailingLabel: String? = null,
    onClick: (() -> Unit)? = null,
) {
    val buttonTextStyle = when (size) {
        SubtextBoxSize.L -> MaterialTheme.typography.paragraph300.merge(
            fontWeight = FontWeight.Medium
        )

        SubtextBoxSize.S, SubtextBoxSize.M -> MaterialTheme.typography.caption200
    }.merge(
        color = Grey400
    )

    val topPaddingValue = when (size) {
        SubtextBoxSize.S -> 28.dp
        SubtextBoxSize.M -> 40.dp
        SubtextBoxSize.L -> 46.dp
    }

    Box(modifier = modifier.wrapContentHeight()) {
        Row(
            modifier = Modifier.padding(
                PaddingValues(
                    top = topPaddingValue,
                    start = 24.dp,
                    end = 24.dp,
                    bottom = 12.dp,
                ),
            ),
            verticalAlignment = Alignment.Bottom,
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.heading600.merge(
                    color = Grey800,
                    fontWeight = FontWeight.Bold,
                )
            )

            Spacer(modifier = Modifier.padding(4.dp))

            Text(
                text = subtitle,
                style = MaterialTheme.typography.caption200.merge(
                    color = Grey400,
                    fontWeight = FontWeight.Normal,
                )
            )

            Spacer(modifier = Modifier.weight(1F))

            if (trailingLabel != null) {
                Text(
                    text = trailingLabel,
                    modifier = Modifier.run {
                        if (onClick != null) {
                            clickable(onClick = onClick)
                        } else {
                            this
                        }
                    },
                    style = buttonTextStyle,
                )
            }
        }
    }
}

@Preview
@Composable
private fun PreviewSubtextBox() {
    Column(
        modifier = Modifier.background(Grey200),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        SubtextBox(
            size = SubtextBoxSize.L,
            title = "title",
            subtitle = "subtitle",
            trailingLabel = "right",
            onClick = {},
            modifier = Modifier.background(White),
        )

        SubtextBox(
            size = SubtextBoxSize.M,
            title = "title",
            subtitle = "subtitle",
            trailingLabel = "right",
            onClick = {},
            modifier = Modifier.background(White),
        )

        SubtextBox(
            size = SubtextBoxSize.S,
            title = "title",
            subtitle = "subtitle",
            trailingLabel = "right",
            onClick = {},
            modifier = Modifier.background(White),
        )
    }
}

@Preview
@Composable
private fun PreviewNoTrailingButtonSubtextBox() {
    Column(
        modifier = Modifier.background(Grey200),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        SubtextBox(
            size = SubtextBoxSize.L,
            title = "title",
            subtitle = "subtitle",
            onClick = {},
            modifier = Modifier.background(White),
        )

        SubtextBox(
            size = SubtextBoxSize.M,
            title = "title",
            subtitle = "subtitle",
            onClick = {},
            modifier = Modifier.background(White),
        )

        SubtextBox(
            size = SubtextBoxSize.S,
            title = "title",
            subtitle = "subtitle",
            onClick = {},
            modifier = Modifier.background(White),
        )
    }
}
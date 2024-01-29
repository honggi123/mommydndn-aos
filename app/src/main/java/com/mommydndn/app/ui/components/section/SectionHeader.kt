package com.mommydndn.app.ui.components.section

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mommydndn.app.ui.theme.Grey400
import com.mommydndn.app.ui.theme.Grey800
import com.mommydndn.app.ui.theme.Typography
import com.mommydndn.app.ui.theme.White
import com.mommydndn.app.ui.theme.heading600
import com.mommydndn.app.ui.theme.paragraph300

@Composable
internal fun SectionHeader(
    title: String,
    modifier: Modifier = Modifier,
    subtitle: String = "",
    trailingText: String = "",
    onTrailingClick: (() -> Unit)? = null,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalAlignment = Alignment.Bottom
    ) {
        Text(
            text = title,
            modifier = Modifier
                .weight(1F)
                .padding(
                    start = 24.dp,
                    top = 46.dp,
                    bottom = 12.dp,
                ),
            color = Grey800,
            style = Typography.heading600.merge(
                fontWeight = FontWeight.Bold,
            )
        )

        Text(
            text = subtitle,
            modifier = Modifier.padding(bottom = 12.dp),
            color = Grey400,
            style = Typography.paragraph300
        )

        Text(
            text = trailingText,
            modifier = Modifier
                .padding(end = 24.dp, bottom = 12.dp)
                .run {
                    if (onTrailingClick != null) {
                        clickable(onClick = onTrailingClick)
                    } else {
                        this
                    }
                },
            color = Grey400,
            style = Typography.paragraph300
        )
    }
}

@Preview
@Composable
private fun SectionHeaderPreview() {
    SectionHeader(
        title = "가장 가까운 시터님",
        modifier = Modifier.background(White),
    )
}
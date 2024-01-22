package com.mommydndn.app.ui.care.details.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.mommydndn.app.ui.theme.Grey100
import com.mommydndn.app.ui.theme.Salmon600
import com.mommydndn.app.ui.theme.paragraph300

@Composable
internal fun DetailsViewMore(
    title: String,
    content: @Composable ColumnScope.() -> Unit,
    contentSpacing: Dp,
    viewMoreText: String,
    onViewMoreClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        DetailsSectionTitle(
            title = title,
            modifier = Modifier.padding(
                start = 24.dp,
                top = 28.dp,
                bottom = 12.dp
            )
        )

        Column(
            modifier = Modifier.padding(horizontal = 24.dp, vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(contentSpacing),
        ) {
            content()
        }

        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp),
            color = Grey100
        )

        Text(
            text = viewMoreText,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .clickable(onClick = onViewMoreClick)
                .padding(vertical = 20.dp),
            color = Salmon600,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.paragraph300
        )
    }
}
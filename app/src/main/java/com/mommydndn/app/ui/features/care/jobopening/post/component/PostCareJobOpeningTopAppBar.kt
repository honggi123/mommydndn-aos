package com.mommydndn.app.ui.features.care.jobopening.post.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mommydndn.app.R
import com.mommydndn.app.ui.components.common.TopAppBarHeight
import com.mommydndn.app.ui.theme.Grey300
import com.mommydndn.app.ui.theme.Grey500
import com.mommydndn.app.ui.theme.Grey700
import com.mommydndn.app.ui.theme.White
import com.mommydndn.app.ui.theme.paragraph300
import com.mommydndn.app.ui.theme.paragraph400

@Composable
internal fun PostCareJobOpeningTopAppBar(
    onCloseClick: () -> Unit,
    onLoadClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.padding(horizontal = 20.dp).height(TopAppBarHeight),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            painter = painterResource(id = R.drawable.icon_close),
            contentDescription = "PostCareJobOpeningTopAppBar_Close",
            modifier = Modifier
                .size(size = 36.dp)
                .padding(4.dp)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                    onClick = onCloseClick,
                ),
            tint = Grey300,
        )

        Text(
            text = stringResource(R.string.post_job_opening),
            modifier = Modifier.weight(1F),
            style = MaterialTheme.typography.paragraph400.merge(
                color = Grey700,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
            )
        )

        Text(
            text = stringResource(R.string.load),
            modifier = Modifier
                .padding(horizontal = 8.dp, vertical = 6.dp)
                .clickable(onClick = onLoadClick),
            style = MaterialTheme.typography.paragraph300.merge(
                color = Grey500,
                fontWeight = FontWeight.Medium,
            )
        )
    }
}

@Preview
@Composable
private fun TopAppBar() {
    PostCareJobOpeningTopAppBar(
        onCloseClick = {},
        onLoadClick = {},
        modifier = Modifier
            .height(68.dp)
            .background(White),
    )
}
package com.mommydndn.app.ui.features.care.provider.register.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
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
import com.mommydndn.app.ui.theme.Grey700
import com.mommydndn.app.ui.theme.White
import com.mommydndn.app.ui.theme.paragraph400

@Composable
internal fun RegisterCareProviderTopAppBar(
    onCloseClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .background(White)
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .height(TopAppBarHeight),
    ) {
        Icon(
            painter = painterResource(id = R.drawable.icon_close),
            contentDescription = "PostCareJobOpeningTopAppBar_Close",
            modifier = Modifier
                .size(size = 36.dp)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                    onClick = onCloseClick,
                )
                .padding(4.dp) // todo
                .align(Alignment.CenterStart),
            tint = Grey300,
        )

        Text(
            text = stringResource(id = R.string.register_care_provider),
            modifier = Modifier.align(Alignment.Center),
            style = MaterialTheme.typography.paragraph400.merge(
                color = Grey700,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
            )
        )
    }
}

@Preview
@Composable
private fun TopAppBar() {
    RegisterCareProviderTopAppBar(
        onCloseClick = {},
        modifier = Modifier,
    )
}
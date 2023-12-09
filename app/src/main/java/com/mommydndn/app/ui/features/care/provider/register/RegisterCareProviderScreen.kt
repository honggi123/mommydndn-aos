package com.mommydndn.app.ui.features.care.provider.register

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.mommydndn.app.R
import com.mommydndn.app.ui.features.care.topAppBarHeight
import com.mommydndn.app.ui.theme.Grey700
import com.mommydndn.app.ui.theme.paragraph400

@Composable
internal fun RegisterCareProviderScreen(
    onCloseClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier.verticalScroll(rememberScrollState())) {

    }

    RegisterCareProviderTopAppBar(
        onCloseClick = { /*TODO*/ },
        modifier = Modifier
            .fillMaxWidth()
            .height(topAppBarHeight),
    )
}

@Composable
private fun RegisterCareProviderTopAppBar(
    onCloseClick: () -> Unit,
    modifier: Modifier = Modifier,
    title: String = stringResource(R.string.register_care_provider),
) {
    Box(modifier = modifier) {
        Icon(
            painter = painterResource(id = R.drawable.icon_close),
            contentDescription = "RegisterCareProviderTopAppBar_Close",
            modifier = Modifier
                .size(36.dp)
                .align(Alignment.CenterStart)
                .offset(x = 20.dp)
                .clickable(onClick = onCloseClick),
        )

        Text(
            text = title,
            modifier = Modifier.align(Alignment.Center),
            style = MaterialTheme.typography.paragraph400.merge(
                color = Grey700,
                fontWeight = FontWeight.Bold,
            )
        )
    }
}
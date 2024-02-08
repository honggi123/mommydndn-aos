package com.mommydndn.app.ui.signin.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mommydndn.app.R
import com.mommydndn.app.ui.theme.Grey500
import com.mommydndn.app.ui.theme.MommydndnTheme
import com.mommydndn.app.ui.theme.Salmon600
import com.mommydndn.app.ui.theme.heading800
import com.mommydndn.app.ui.theme.paragraph300

@Composable
fun LogoWithIntroduction(
    modifier: Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            painter = painterResource(id = R.drawable.icon_logo),
            contentDescription = "마미든든 로고",
            modifier = Modifier.size(124.dp),
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = stringResource(id = R.string.find_babysitters_near_me),
            color = Salmon600,
            style = MaterialTheme.typography.heading800.merge(
                fontWeight = FontWeight.Bold,
            ),
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = stringResource(id = R.string.find_babysitters_near_me_description),
            color = Grey500,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.paragraph300.merge(
                fontWeight = FontWeight.Normal,
            )
        )
    }
}

@Preview
@Composable
private fun LogoWithIntroduction_Preview() {
    MommydndnTheme {
        LogoWithIntroduction(
            modifier = Modifier
                .fillMaxWidth()
        )
    }
}
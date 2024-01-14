package com.mommydndn.app.ui.care.components.post

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mommydndn.app.R
import com.mommydndn.app.ui.theme.Grey400
import com.mommydndn.app.ui.theme.Grey700
import com.mommydndn.app.ui.theme.White
import com.mommydndn.app.ui.theme.caption200
import com.mommydndn.app.ui.theme.paragraph300

@Composable
fun PostSectionTitle(
    title: String,
    subtitle: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalAlignment = Alignment.Bottom,
    ) {
        Text(
            text = title,
            modifier = Modifier,
            style = MaterialTheme.typography.paragraph300.merge(
                color = Grey700,
                fontWeight = FontWeight.W700,
            ),
        )

        Text(
            text = subtitle,
            modifier = Modifier,
            style = MaterialTheme.typography.caption200.merge(
                color = Grey400,
                fontWeight = FontWeight.W400,
            ),
        )
    }
}

@Preview
@Composable
private fun PostFieldTitle_Preview() {
    PostSectionTitle(
        title = stringResource(R.string.bio),
        subtitle = stringResource(id = R.string.required),
        modifier = Modifier
            .fillMaxWidth()
            .background(White)
            .padding(start = 24.dp, top = 28.dp, bottom = 12.dp),
    )
}
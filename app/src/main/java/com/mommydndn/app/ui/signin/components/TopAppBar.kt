package com.mommydndn.app.ui.signin.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mommydndn.app.R
import com.mommydndn.app.ui.care.post.components.TopAppBarHeight
import com.mommydndn.app.ui.theme.Grey500
import com.mommydndn.app.ui.theme.White
import com.mommydndn.app.ui.theme.paragraph300

@Composable
internal fun SignInTopAppBar(
    onExploreClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier.height(TopAppBarHeight)) {
        TextButton(
            onClick = onExploreClick,
            modifier = Modifier.align(Alignment.CenterEnd).padding(end = 20.dp),
            contentPadding = PaddingValues(horizontal = 12.dp, vertical = 6.dp),
        ) {
            Text(
                text = stringResource(id = R.string.explore),
                modifier = Modifier,
                color = Grey500,
                style = MaterialTheme.typography.paragraph300.copy(
                    fontWeight = FontWeight.Medium,
                )
            )
        }
    }
}

@Preview
@Composable
private fun TopAppBar_Preview() {
    SignInTopAppBar(
        onExploreClick = {},
        modifier = Modifier
            .background(White)
            .fillMaxWidth(),
    )
}
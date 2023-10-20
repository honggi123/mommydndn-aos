package com.mommydndn.app.ui.components.common

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mommydndn.app.R
import com.mommydndn.app.ui.theme.Grey50
import com.mommydndn.app.ui.theme.Grey600
import com.mommydndn.app.ui.theme.MommydndnaosTheme
import com.mommydndn.app.ui.theme.Shapes
import com.mommydndn.app.ui.theme.White
import com.mommydndn.app.ui.theme.paragraph300
import com.mommydndn.app.ui.theme.shadow500

@Composable
fun Toast(
    modifier: Modifier = Modifier,
    @StringRes id: Int? = null,
    text: String = "",
) {
    Box(
        modifier = modifier
            .border(
                width = 1.dp,
                color = Grey50,
                shape = Shapes.large
            )
            .width(189.dp)
            .height(48.dp)
            .then(shadow500)
            .background(color = White, shape = Shapes.large)
            .padding(start = 18.dp, top = 12.dp, end = 18.dp, bottom = 12.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                modifier = Modifier.size(24.dp),
                painter = painterResource(id = R.drawable.ic_check_circle), contentDescription = ""
            )
            Spacer(modifier = Modifier.size(10.dp))
            Text(
                text = id?.let { stringResource(id = id) } ?: text,
                style = MaterialTheme.typography.paragraph300.copy(
                    fontWeight = FontWeight.Medium,
                    color = Grey600,
                    platformStyle = PlatformTextStyle(
                        includeFontPadding = false
                    )
                )
            )
        }
    }
}

@Preview
@Composable
fun previewToast() {
    MommydndnaosTheme {
        Box(modifier = Modifier
            .fillMaxSize()
            .background(White)) {
            Toast(text = "toast")
        }
    }
}

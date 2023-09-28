package com.mommydndn.app.ui.component.common.Item

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mommydndn.app.R
import com.mommydndn.app.ui.theme.Grey200
import com.mommydndn.app.ui.theme.Grey50
import com.mommydndn.app.ui.theme.Grey700
import com.mommydndn.app.ui.theme.MommydndnaosTheme
import com.mommydndn.app.ui.theme.Salmon600
import com.mommydndn.app.ui.theme.Shapes
import com.mommydndn.app.ui.theme.White
import com.mommydndn.app.ui.theme.paragraph300

@Composable
fun RadioListItem(
    checked: Boolean,
    onCheckedChange: ((Boolean) -> Unit),
    text: String,
    modifier: Modifier = Modifier
) {

    Crossfade(
        targetState = checked,
        label = "",
        animationSpec = tween(durationMillis = 400)
    ) { isChecked ->
        Box(
            modifier = modifier
                .width(342.dp)
                .wrapContentHeight()
                .background(color = if (checked) Grey50 else White, shape = Shapes.large)
                .clickable {
                    onCheckedChange(!checked)
                }
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 12.dp, top = 16.dp, end = 12.dp, bottom = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = Modifier.size(32.dp),
                    painter = painterResource(
                        id = if (isChecked) R.drawable.ic_radio_true else R.drawable.ic_radio_false
                    ),
                    contentDescription = "",
                    tint = if (isChecked) Salmon600 else Grey200
                )

                Spacer(modifier = Modifier.size(4.dp))
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = text, style = MaterialTheme.typography.paragraph300.copy(
                        fontWeight = FontWeight.Normal,
                        color = Grey700,
                        platformStyle = PlatformTextStyle(
                            includeFontPadding = false
                        )
                    )
                )
            }
        }
    }
}

@Preview
@Composable
fun previewListItem() {
    MommydndnaosTheme {
        var state by remember { mutableStateOf(false) }
        RadioListItem(state, { state = it }, "리스트 아이템")
    }
}
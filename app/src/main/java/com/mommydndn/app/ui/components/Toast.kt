package com.mommydndn.app.ui.components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.mommydndn.app.R
import com.mommydndn.app.ui.theme.Grey50
import com.mommydndn.app.ui.theme.Grey600
import com.mommydndn.app.ui.theme.Shadow500
import com.mommydndn.app.ui.theme.White
import com.mommydndn.app.ui.theme.paragraph300
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

@Composable
fun Toast(
    iconPainter: Painter,
    message: String,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier
                .then(Shadow500)
                .border(
                    width = 1.dp,
                    color = Grey50,
                    shape = RoundedCornerShape(12.dp)
                )
                .background(color = White, shape = RoundedCornerShape(12.dp))
                .padding(horizontal = 18.dp, vertical = 12.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                painter = iconPainter,
                contentDescription = "Toast_Icon",
                modifier = Modifier.size(24.dp),
                tint = Color.Unspecified,
            )

            Text(
                text = message,
                color = Grey600,
                style = MaterialTheme.typography.paragraph300.merge(
                    fontWeight = FontWeight.Medium,
                )
            )
        }
    }
}

@Preview
@Composable
private fun Toast_Preview() {
    val coroutineScope = rememberCoroutineScope()

    val density = LocalDensity.current

    val offsetValue = with(density) {
        64.dp.toPx()
    }

    val offsetY = remember {
        Animatable(-offsetValue)
    }

    LaunchedEffect(Unit) {
        coroutineScope.launch {
            offsetY.animateTo(
                targetValue = offsetValue,
                animationSpec = tween(
                    durationMillis = 1_000
                )
            )
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(White),
    ) {
        Toast(
            iconPainter = painterResource(id = R.drawable.ic_check_circle),
            message = "로그인 성공했어요!",
            modifier = Modifier.offset {
                IntOffset(
                    x = 0,
                    y = offsetY.value.roundToInt(),
                )
            },
        )
    }
}

package com.mommydndn.app.ui.component.button

import android.graphics.BlurMaskFilter
import androidx.annotation.StringRes
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.mommydndn.app.R
import com.mommydndn.app.ui.theme.Grey100
import com.mommydndn.app.ui.theme.Grey50
import com.mommydndn.app.ui.theme.Grey600
import com.mommydndn.app.ui.theme.MommydndnaosTheme
import com.mommydndn.app.ui.theme.Shapes
import com.mommydndn.app.ui.theme.paragraph500

@Composable
fun SquareButton(
    status: Boolean = false,
    imageResourceId: Int,
    text: String = "",
    onClick: () -> Unit,
) {
    var isSelected by remember { mutableStateOf(status) }

    val backgroundColor by animateColorAsState(
        targetValue = if (isSelected) Grey100 else Grey50,
        animationSpec = tween(durationMillis = 100,easing = FastOutSlowInEasing)
    )

    Box(
        modifier =
        Modifier
            .width(163.dp)
            .background(
                color = backgroundColor,
                shape = Shapes.large
            )
            .padding(24.dp)
            .clickable(onClick = {
                isSelected = !isSelected
                onClick
            }),
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.Top),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                painter = painterResource(id = imageResourceId),
                contentDescription = "",
                modifier = Modifier
                    .size(72.dp)
                    .padding(0.9.dp)
            )
            Text(
                text = text,
                style = MaterialTheme.typography.paragraph500.copy(
                    fontWeight = FontWeight.Bold,
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
fun previewSquareButton() {
    MommydndnaosTheme {
        SquareButton(
            status = true,
            imageResourceId = R.drawable.person_graphic,
            text = "text"
        ) {}
    }
}
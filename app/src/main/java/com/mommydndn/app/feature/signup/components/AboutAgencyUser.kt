package com.mommydndn.app.feature.signup.components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.mommydndn.app.R
import com.mommydndn.app.ui.theme.Grey300
import com.mommydndn.app.ui.theme.Grey50
import com.mommydndn.app.ui.theme.Grey500
import com.mommydndn.app.ui.theme.Grey600
import com.mommydndn.app.ui.theme.Shadow500
import com.mommydndn.app.ui.theme.White
import com.mommydndn.app.ui.theme.paragraph300
import kotlinx.coroutines.launch

@Composable
internal fun AboutAgencyUser(
    onCloseClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Surface(
        modifier = modifier.then(Shadow500),
        shape = RoundedCornerShape(16.dp),
        color = White,
        border = BorderStroke(1.dp, Grey50),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(horizontal = 16.dp, vertical = 20.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Box(
                    modifier = Modifier
                        .background(Grey50, CircleShape)
                        .padding(horizontal = 7.5.dp, vertical = 8.5.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.icon_building),
                        contentDescription = "AboutAgencyUserType_Building",
                        modifier = Modifier.size(36.dp),
                        tint = Color.Unspecified,
                    )
                }

                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text(
                        text = stringResource(R.string.about_agency_user),
                        color = Grey600,
                        style = MaterialTheme.typography.paragraph300.merge(
                            fontWeight = FontWeight.Bold
                        )
                    )

                    Text(
                        text = stringResource(R.string.about_agency_user_description),
                        color = Grey500,
                        style = MaterialTheme.typography.paragraph300.merge(
                            fontWeight = FontWeight.Normal
                        )
                    )
                }
            }

            Icon(
                painter = painterResource(id = R.drawable.icon_close),
                contentDescription = "AboutAgencyUserType_Close",
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(12.dp)
                    .size(24.dp)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                        onClick = onCloseClick,
                    ),
                tint = Grey300,
            )
        }
    }
}

@Preview
@Composable
private fun AboutAgencyUser_Preview() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(White),
    ) {
        AboutAgencyUser(
            onCloseClick = {},
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(horizontal = 16.dp, vertical = 24.dp)
        )
    }
}

@Preview
@Composable
private fun AboutAgencyUser_Transition() {
    // todo
    val coroutineScope = rememberCoroutineScope()

    val offsetY = remember { Animatable(0F) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(White),
    ) {
        AboutAgencyUser(
            onCloseClick = {
                coroutineScope.launch {
                    offsetY.animateTo(
                        targetValue = 512F,
                        animationSpec = tween(
                            durationMillis = 500,
                            easing = LinearEasing,
                        )
                    )
                }
            },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(horizontal = 16.dp, vertical = 24.dp)
                .offset {
                    IntOffset(0, offsetY.value.toInt())
                }
        )
    }
}
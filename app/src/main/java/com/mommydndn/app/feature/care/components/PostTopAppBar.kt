package com.mommydndn.app.feature.care.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.mommydndn.app.R
import com.mommydndn.app.ui.theme.Grey300
import com.mommydndn.app.ui.theme.Grey700
import com.mommydndn.app.ui.theme.White
import com.mommydndn.app.ui.theme.paragraph400

val TopAppBarHeight: Dp = 68.dp

@Composable
fun PostTopAppBar(
    leading: @Composable BoxScope.() -> Unit,
    onLeadingClick: () -> Unit,
    title: String,
    modifier: Modifier = Modifier,
    trailing: (@Composable BoxScope.() -> Unit)? = null,
    onTrailingClick: (() -> Unit)? = null,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(White)
            .height(TopAppBarHeight)
            .padding(horizontal = 20.dp),
    ) {
        Box(
            modifier = Modifier
                .align(Alignment.CenterStart)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                    onClick = onLeadingClick,
                ),
            content = leading,
        )

        Text(
            text = title,
            modifier = Modifier.align(Alignment.Center),
            style = MaterialTheme.typography.paragraph400.merge(
                color = Grey700,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
            )
        )

        if (trailing != null) {
            Box(
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .run {
                        if (onTrailingClick != null) {
                            clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication = null,
                                onClick = onTrailingClick,
                            )
                        } else {
                            this
                        }
                    },
                content = trailing,
            )
        }
    }
}

@Preview
@Composable
private fun TopAppBar_NoTrailing() {
    PostTopAppBar(
        leading = {
            Icon(
                painter = painterResource(id = R.drawable.icon_arrow_left),
                contentDescription = "MommyDnDnTopAppBar_ArrowLeft",
                modifier = Modifier.size(size = 36.dp),
                tint = Grey300,
            )
        },
        onLeadingClick = {},
        title = "업체 프로필 미리보기",
        modifier = Modifier,
    )
}
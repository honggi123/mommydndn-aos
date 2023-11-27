package com.mommydndn.app.ui.components.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.mommydndn.app.ui.theme.White
import com.mommydndn.app.ui.theme.WhiteOpacity600

@Composable
internal fun Header(
    modifier: Modifier = Modifier,
    backgroundColor: Color = White,
    leftContent: @Composable RowScope.() -> Unit = {},
    centerContent: @Composable RowScope.() -> Unit = {},
    rightContent: @Composable RowScope.() -> Unit = {}
) {
    TopAppBar(
        modifier = modifier.fillMaxWidth().height(68.dp),
        contentPadding = PaddingValues(
            start = 20.dp,
            top = 16.dp,
            end = 20.dp,
            bottom = 16.dp
        ),
        elevation = 0.dp,
        backgroundColor = backgroundColor
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically,
                content = leftContent
            )

            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                content = centerContent
            )

            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically,
                content = rightContent
            )
        }
    }
}





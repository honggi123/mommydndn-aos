package com.mommydndn.app.ui.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.mommydndn.app.R
import com.mommydndn.app.ui.care.post.components.TopAppBarHeight
import com.mommydndn.app.ui.theme.White

@Composable
internal fun HomeTopAppBar(
    onInquiryClick: () -> Unit,
    onNotificationClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .background(White)
            .fillMaxWidth()
            .height(TopAppBarHeight),
    ) {
        Icon(
            painter = painterResource(id = R.drawable.icon_logo),
            contentDescription = "HomeTopAppBar_Logo",
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(start = 20.dp)
                .size(36.dp),
            tint = Color.Unspecified,
        )

        Row(
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .padding(end = 20.dp),
            horizontalArrangement = Arrangement.spacedBy(2.dp),
        ) {
            Icon(
                painter = painterResource(id = R.drawable.icon_headset),
                contentDescription = "HomeTopAppBar_Headset",
                modifier = Modifier
                    .size(36.dp)
                    .clickable(onClick = onInquiryClick),
                tint = Color.Unspecified,
            )

            Icon(
                painter = painterResource(id = R.drawable.icon_bell),
                contentDescription = "HomeTopAppBar_Bell",
                modifier = Modifier
                    .size(36.dp)
                    .clickable(onClick = onNotificationClick),
                tint = Color.Unspecified,
            )
        }
    }
}
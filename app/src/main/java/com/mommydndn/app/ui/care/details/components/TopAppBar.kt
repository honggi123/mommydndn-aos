package com.mommydndn.app.ui.care.details.components

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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.mommydndn.app.R
import com.mommydndn.app.ui.care.post.components.TopAppBarHeight
import com.mommydndn.app.ui.theme.Grey300
import com.mommydndn.app.ui.theme.Grey400

@Composable
internal fun DetailsTopAppBar(
    onBackClick: () -> Unit,
    onInquiryClick: () -> Unit,
    onBlockClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(TopAppBarHeight),
        ) {
            Icon(
                painter = painterResource(id = R.drawable.icon_arrow_left),
                contentDescription = "뒤로 가기",
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(start = 20.dp)
                    .size(36.dp)
                    .clickable(onClick = onBackClick),
                tint = Grey400
            )

            Row(
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .padding(end = 20.dp),
                horizontalArrangement = Arrangement.spacedBy(2.dp),
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.icon_headset),
                    contentDescription = "문의하기",
                    modifier = Modifier
                        .size(36.dp)
                        .clickable(onClick = onInquiryClick),
                    tint = Grey300
                )

                Icon(
                    painter = painterResource(id = R.drawable.icon_block),
                    contentDescription = "차단하기",
                    modifier = Modifier
                        .size(36.dp)
                        .clickable(onClick = onBlockClick),
                    tint = Grey300
                )
            }
        }
    }
}
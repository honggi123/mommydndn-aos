package com.mommydndn.app.ui.components.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.mommydndn.app.data.model.Banner
import com.mommydndn.app.ui.theme.MommydndnaosTheme
import com.mommydndn.app.ui.theme.White
import com.mommydndn.app.ui.theme.caption100


@OptIn(ExperimentalPagerApi::class)
@Composable
fun BannerList(
    items: List<Banner>
) {
    val pagerState = rememberPagerState()

    HorizontalPager(state = pagerState, count = items.size) { page ->
        Banner(
            imgUrl = items[page].url,
            pageNum = page + 1,
            totalPageNum = items.size,
            onClick = {}
        )
    }
}

@Composable
fun Banner(
    imgUrl: String,
    pageNum: Int,
    totalPageNum: Int,
    onClick: (() -> Unit)
) {
    val painter = rememberImagePainter(
        data = imgUrl,
        builder = {
            crossfade(true)
        }
    )

    Box(
        modifier = Modifier
            .width(390.dp)
            .height(200.dp)
            .clickable { onClick() },
    ) {
        Image(
            painter = painter,
            contentDescription = null,
            modifier = Modifier.fillMaxSize()
        )
        Box(
            contentAlignment = Alignment.BottomEnd,
            modifier = Modifier
                .fillMaxSize()
        ) {
            Row(
                modifier = Modifier
                    .width(36.dp)
                    .padding(bottom = 8.dp, end = 8.dp)
                    .background(color = Color(0x40000000), shape = RoundedCornerShape(20.dp)),
                horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterHorizontally),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = "$pageNum/$totalPageNum",
                    style = MaterialTheme.typography.caption100.copy(
                        fontWeight = FontWeight.Normal,
                        color = White,
                        platformStyle = PlatformTextStyle(
                            includeFontPadding = false
                        ),
                        textAlign = TextAlign.Center
                    )
                )
            }

        }
    }
}

@Preview
@Composable
fun previewBanner() {
    MommydndnaosTheme {
        Banner(
            imgUrl = "https://via.placeholder.com/400x200.png",
            pageNum = 1,
            totalPageNum = 3
        ) {}
    }
}

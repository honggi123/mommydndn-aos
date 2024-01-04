package com.mommydndn.app.ui.deprecated.components.list

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.mommydndn.app.domain.model.banner.Banner
import com.mommydndn.app.ui.theme.MommydndnTheme
import com.mommydndn.app.ui.theme.Grey100
import com.mommydndn.app.ui.theme.White
import com.mommydndn.app.ui.theme.caption100


@OptIn(ExperimentalPagerApi::class)
@Composable
fun BannerList(
    modifier: Modifier = Modifier,
    items: List<Banner>?
) {
    if (!items.isNullOrEmpty()) {
        val pagerState = rememberPagerState()

        HorizontalPager(modifier = modifier, state = pagerState, count = items.size) { page ->
            Banner(
                imgUrl = items[page].url,
                pageNum = page + 1,
                totalPageNum = items.size,
                onClick = {}
            )
        }
    } else {
        Box(modifier = modifier.background(Grey100))
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

    val imageApectRatio = 1.95f / 1f

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(imageApectRatio)
            .clickable { onClick() },
    ) {
        Image(
            painter = painter,
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Box(
            contentAlignment = Alignment.BottomEnd,
            modifier = Modifier
                .fillMaxSize()
        ) {
            Row(
                modifier = Modifier
                    .padding(bottom = 8.dp, end = 8.dp)
                    .background(color = Color(0x40000000), shape = RoundedCornerShape(20.dp)),
                horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterHorizontally),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    modifier = Modifier.padding(horizontal = 8.dp),
                    text = "$pageNum/$totalPageNum",
                    style = MaterialTheme.typography.caption100.copy(
                        fontWeight = FontWeight.Normal,
                        color = White,
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
    MommydndnTheme {
        Banner(
            imgUrl = "https://via.placeholder.com/400x200.png",
            pageNum = 1,
            totalPageNum = 3
        ) {}
    }
}

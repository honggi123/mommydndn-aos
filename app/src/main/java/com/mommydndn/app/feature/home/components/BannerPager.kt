package com.mommydndn.app.feature.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import com.mommydndn.app.ui.theme.White
import com.mommydndn.app.ui.theme.caption100

data class Banner(
    val id: Int,
    val imageUrl: String,
    val url: String,
)

@Composable
internal fun BannerPager(
    banners: List<Banner>,
    modifier: Modifier = Modifier,
    pagerState: PagerState = rememberPagerState(),
    ratio: Float = 1.95F, // TODO
) {
    Box(modifier = modifier) {
        HorizontalPager(
            count = banners.size,
            modifier = Modifier
                .aspectRatio(ratio)
                .fillMaxWidth(),
            state = pagerState,
        ) { page ->
            with(banners[page]) {
                BannerContent(
                    imageUrl = imageUrl,
                    onClick = {
                        // open_url
                        url
                    },
                    modifier = Modifier,
                )
            }
        }

        Text(
            text = "${pagerState.currentPage + 1}/${pagerState.pageCount}",
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(end = 8.dp, bottom = 8.dp)
                .background(
                    color = Color(0x40000000),
                    shape = RoundedCornerShape(20.dp)
                )
                .padding(horizontal = 9.5.dp, vertical = 2.dp),
            color = White,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.caption100.merge(
                fontWeight = FontWeight.Normal,
            ),
        )
    }
}

@Composable
private fun BannerContent(
    imageUrl: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Image(
        painter = rememberAsyncImagePainter(model = imageUrl),
        contentDescription = "BannerContent_Image",
        modifier = modifier
            .fillMaxSize()
            .clickable(
                interactionSource = remember {
                    MutableInteractionSource()
                },
                indication = null,
                onClick = onClick,
            ),
        contentScale = ContentScale.FillWidth,
    )
}


@OptIn(ExperimentalPagerApi::class)
@Preview
@Composable
private fun BannerPager_Preview() {
    val banners = buildList {
        add(
            Banner(
                id = 0,
                imageUrl = "https://images.pexels.com/photos/11634900/pexels-photo-11634900.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2",
                url = ""
            )
        )
        add(
            Banner(
                id = 1,
                imageUrl = "https://images.pexels.com/photos/7050090/pexels-photo-7050090.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2",
                url = ""
            )
        )
    }

    val pagerState = rememberPagerState()

    BannerPager(
        banners = banners,
        modifier = Modifier,
        pagerState = pagerState,
    )
}
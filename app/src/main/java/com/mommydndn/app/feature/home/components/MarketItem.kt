package com.mommydndn.app.feature.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.mommydndn.app.BuildConfig
import com.mommydndn.app.R
import com.mommydndn.app.ui.theme.Grey50
import com.mommydndn.app.ui.theme.Grey500
import com.mommydndn.app.ui.theme.Grey700
import com.mommydndn.app.ui.theme.Grey800
import com.mommydndn.app.ui.theme.White
import com.mommydndn.app.ui.theme.caption100
import com.mommydndn.app.ui.theme.caption200
import com.mommydndn.app.ui.theme.paragraph400

@Composable
internal fun MarketItem(
    imageUrl: String,
    isLiked: Boolean,
    price: String,
    title: String,
    neighborhood: String,
    time: String,
    modifier: Modifier = Modifier,
) {
    val productImagePainter = rememberAsyncImagePainter(imageUrl) // todo: crossfade

    val isLikedImagePainter = rememberAsyncImagePainter(
        model = if (isLiked) {
            R.drawable.icon_heart_fill_salmon
        } else {
            R.drawable.icon_heart_fill
        }
    )

    Box(modifier = modifier) {
        Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(165F / 132F)
                    .run {
                        if (BuildConfig.DEBUG) {
                            background(color = Grey50, shape = RoundedCornerShape(10.dp))
                        } else {
                            this
                        }
                    }
            ) {
                Image(
                    painter = productImagePainter,
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(10.dp)),
                    contentScale = ContentScale.Crop,
                )

                Image(
                    painter = isLikedImagePainter,
                    contentDescription = null,
                    modifier = Modifier
                        .size(36.dp)
                        .padding(end = 6.dp, bottom = 6.dp)
                        .align(Alignment.BottomEnd),
                )
            }

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = price,
                color = Grey800,
                style = MaterialTheme.typography.paragraph400.merge(
                    fontWeight = FontWeight.Bold,
                )
            )

            Text(
                text = title,
                color = Grey700,
                style = MaterialTheme.typography.caption200.merge(
                    fontWeight = FontWeight.Normal,
                )
            )

            Text(
                text = "$neighborhood ・ $time",
                color = Grey500,
                style = MaterialTheme.typography.caption100.merge(
                    fontWeight = FontWeight.Normal,
                )
            )
        }
    }
}

@Preview
@Composable
private fun MarketListItem_Preview() {
    MarketItem(
        imageUrl = "",
        isLiked = true,
        price = "5,000원",
        title = "토끼 인형",
        neighborhood = "서초구",
        time = "1시간 전",
        modifier = Modifier
            .background(White)
            .width(165.dp)
            .padding(horizontal = 8.dp, vertical = 12.dp),
    )
}


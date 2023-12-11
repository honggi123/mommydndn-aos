package com.mommydndn.app.ui.features.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.mommydndn.app.ui.theme.Grey700
import com.mommydndn.app.ui.theme.Grey800
import com.mommydndn.app.ui.theme.White
import com.mommydndn.app.ui.theme.caption200
import com.mommydndn.app.ui.theme.paragraph400
import com.mommydndn.app.R
import com.mommydndn.app.data.model.babyitem.BabyItem
import com.mommydndn.app.ui.theme.Grey500
import com.mommydndn.app.util.NumberUtils
import com.mommydndn.app.util.DateTimeUtils

@Composable
fun MarketListItemBox(
    item: BabyItem,
    modifier: Modifier = Modifier
) {
    val productPainter = rememberImagePainter(
        data = item.imageUrl,
        builder = {
            crossfade(true)
        }
    )

    val isLikedPainter = rememberImagePainter(
        data = if (item.isLiked) R.drawable.ic_heart_fill_salmon else R.drawable.ic_heart_fill,
    )

    Box(
        modifier = modifier
            .aspectRatio(165f / 216f)
            .background(color = White, shape = RoundedCornerShape(10.dp))
    ) {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(132f / 216f)
            ) {
                Image(
                    painter = productPainter,
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(10.dp)),
                    contentScale = ContentScale.Crop
                )

                Image(
                    painter = isLikedPainter,
                    contentDescription = null,
                    modifier = Modifier
                        .size(36.dp)
                        .padding(end = 6.dp, bottom = 6.dp)
                        .align(Alignment.BottomEnd)
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = NumberUtils.getPriceString(item.price),
                style = MaterialTheme.typography.paragraph400.merge(
                    fontWeight = FontWeight.Bold,
                    color = Grey800,
                    textAlign = TextAlign.Center
                )
            )
            Text(
                text = item.title,
                style = MaterialTheme.typography.caption200.merge(
                    fontWeight = FontWeight.Normal,
                    color = Grey700,
                    textAlign = TextAlign.Center
                )
            )
            Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = item.neighborhood,
                    style = MaterialTheme.typography.caption200.merge(
                        fontWeight = FontWeight.Normal,
                        color = Grey500,
                        textAlign = TextAlign.Center
                    )
                )

                Image(
                    painter = painterResource(id = R.drawable.icon_circle),
                    contentDescription = "icon/circle",
                    modifier = Modifier.align(Alignment.CenterVertically)
                )

                Text(
                    text = DateTimeUtils.getFormattedTimeAgo(item.createdAt),
                    style = MaterialTheme.typography.caption200.merge(
                        fontWeight = FontWeight.Normal,
                        color = Grey500,
                        textAlign = TextAlign.Center
                    )
                )
            }
        }
    }
}

@Preview
@Composable
fun MarketListScreen() {
    val babyItems = listOf(
        BabyItem(1, "", 10000, "Item 1", "Neighborhood 1", System.currentTimeMillis(), false),
        BabyItem(2, "", 15000, "Item 2", "Neighborhood 2", System.currentTimeMillis(), true),
    )

    babyItems.chunked(2).forEach { rowItems ->
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            rowItems.forEach { item ->
                MarketListItemBox(modifier = Modifier.weight(1f), item = item)
            }
        }
    }
}


package com.mommydndn.app.ui.components.box

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.mommydndn.app.ui.theme.Grey700
import com.mommydndn.app.ui.theme.Grey800
import com.mommydndn.app.ui.theme.White
import com.mommydndn.app.ui.theme.caption200
import com.mommydndn.app.ui.theme.paragraph400
import com.mommydndn.app.R
import com.mommydndn.app.data.model.BabyItem
import com.mommydndn.app.data.model.MarketListItem
import com.mommydndn.app.ui.theme.Grey500

@Composable
fun MarketListItemBox(
    item: BabyItem
) {
    val productPainter = rememberImagePainter(
        data = item.imageUrl,
        builder = {
            crossfade(true)
        }
    )

    Box(
        modifier = Modifier
            .width(165.dp)
            .height(216.dp)
            .background(color = White, shape = RoundedCornerShape(10.dp))
    ) {
        Column {
            Box(
                modifier = Modifier
                    .width(165.dp)
                    .height(132.dp)
            ) {
                Image(
                    painter = productPainter,
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(10.dp)),
                    contentScale = ContentScale.Crop
                )
            }
            Spacer(modifier = Modifier.padding(8.dp))
            Text(
                text = item.price.toString(),
                style = MaterialTheme.typography.paragraph400.copy(
                    fontWeight = FontWeight.Bold,
                    color = Grey800,
                    platformStyle = PlatformTextStyle(
                        includeFontPadding = false
                    ),
                    textAlign = TextAlign.Center
                )
            )
            Text(
                text = item.title,
                style = MaterialTheme.typography.caption200.copy(
                    fontWeight = FontWeight.Normal,
                    color = Grey700,
                    platformStyle = PlatformTextStyle(
                        includeFontPadding = false
                    ),
                    textAlign = TextAlign.Center
                )
            )
            Row {
                Text(
                    text = item.neighborhood,
                    style = MaterialTheme.typography.caption200.copy(
                        fontWeight = FontWeight.Normal,
                        color = Grey500,
                        platformStyle = PlatformTextStyle(
                            includeFontPadding = false
                        ),
                        textAlign = TextAlign.Center
                    )
                )
                Spacer(modifier = Modifier.padding(4.dp))
                Image(
                    painter = painterResource(id = R.drawable.ic_ellipse),
                    contentDescription = null,
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
                Spacer(modifier = Modifier.padding(4.dp))

                Text(
                    text = item.createdAt.toString(),
                    style = MaterialTheme.typography.caption200.copy(
                        fontWeight = FontWeight.Normal,
                        color = Grey500,
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



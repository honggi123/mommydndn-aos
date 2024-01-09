package com.mommydndn.app.deprecated.components.box

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.mommydndn.app.R
import com.mommydndn.app.ui.theme.Grey50
import com.mommydndn.app.ui.theme.Grey500
import com.mommydndn.app.ui.theme.Grey600
import com.mommydndn.app.ui.theme.Grey700
import com.mommydndn.app.ui.theme.Salmon600
import com.mommydndn.app.ui.theme.White
import com.mommydndn.app.ui.theme.WhiteOpacity500
import com.mommydndn.app.ui.theme.caption200
import com.mommydndn.app.ui.theme.paragraph300
import kotlinx.coroutines.withContext
import net.daum.mf.map.api.MapView
import net.daum.mf.map.api.CameraPosition
import net.daum.mf.map.api.CameraUpdateFactory
import net.daum.mf.map.api.MapPOIItem
import net.daum.mf.map.api.MapPoint


@Composable
fun MapContainerBox(
    modifier: Modifier = Modifier,
    mapView: MapView,
    addressText: String = "",
    latitude: Double,
    longtitude: Double,
    openMapAction: () -> Unit = {}
) {
    val mapAspectRatio = 2.23f / 1f

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .fillMaxWidth()
            .background(color = White)
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(shape = RoundedCornerShape(12.dp))
                .border(
                    border = BorderStroke(1.dp, Grey50),
                    shape = RoundedCornerShape(12.dp)
                )
        ) {
            MapViewContainer(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(mapAspectRatio),
                mapView = mapView,
                latitude = latitude,
                longtitude = longtitude
            )

            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.Top),
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(shape = RoundedCornerShape(bottomStart = 12.dp, bottomEnd = 12.dp))
                    .background(color = White)
                    .padding(
                        horizontal = 16.dp,
                        vertical = 12.dp
                    )
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(4.dp, Alignment.Top),
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(4.dp, Alignment.Start),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_marker),
                            contentDescription = "",
                            tint = Grey700
                        )
                        Text(
                            text = addressText,
                            style = MaterialTheme.typography.caption200.copy(
                                fontWeight = FontWeight.Medium,
                                color = Grey700
                            ),
                            modifier = Modifier
                                .wrapContentHeight(align = Alignment.CenterVertically)
                        )
                    }
                }
                Divider(
                    color = Grey50,
                    modifier = Modifier
                        .fillMaxWidth()
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { openMapAction() },
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(
                            4.dp,
                            Alignment.CenterHorizontally
                        ),
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .requiredWidth(width = 310.dp)
                            .padding(horizontal = 16.dp, vertical = 12.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_open),
                            contentDescription = "Icon/open"
                        )
                        Text(
                            text = "카카오맵으로 보기",
                            style = MaterialTheme.typography.paragraph300.copy(
                                fontWeight = FontWeight.Medium,
                                color = Grey500
                            )
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun MapViewContainer(
    modifier: Modifier = Modifier,
    mapView: MapView,
    latitude: Double,
    longtitude: Double
) {

    val jobOfferMapPoint = MapPoint.mapPointWithGeoCoord(
        latitude,
        longtitude
    )

    val marker = MapPOIItem().apply {
        itemName = ""
        mapPoint = jobOfferMapPoint
        markerType = MapPOIItem.MarkerType.CustomImage
        customImageResourceId = R.drawable.ic_map_marker
        isCustomImageAutoscale = false      // 커스텀 마커 이미지 크기 자동 조정

    }

    val position = CameraPosition(jobOfferMapPoint, 3f)

    mapView.addPOIItem(marker)
    val cameraUpdate = CameraUpdateFactory.newCameraPosition(position);
    mapView.moveCamera(cameraUpdate)

    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        AndroidView(
            factory = {
                mapView
            },
            modifier = Modifier
                .fillMaxSize()
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(30.dp)
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            Color.White,
                        ),
                        startY = 0.0f,
                        endY = 1.0F / 0.0F
                    ),
                )
                .align(Alignment.BottomCenter)
        )
    }
}



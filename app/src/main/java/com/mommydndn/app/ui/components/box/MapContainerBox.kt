package com.mommydndn.app.ui.components.box

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidth
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.kakao.vectormap.MapView
import com.mommydndn.app.R
import com.mommydndn.app.ui.theme.Grey50
import com.mommydndn.app.ui.theme.Grey500
import com.mommydndn.app.ui.theme.Grey600
import com.mommydndn.app.ui.theme.Grey700
import com.mommydndn.app.ui.theme.Salmon600
import com.mommydndn.app.ui.theme.White
import com.mommydndn.app.ui.theme.caption200
import com.mommydndn.app.ui.theme.paragraph300
import kotlinx.coroutines.withContext


@Composable
fun MapContainerBox(
    modifier: Modifier = Modifier,
    mapView: MapView
) {
    val mapAspectRatio = 2.23f / 1f

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .fillMaxWidth()
            .background(color = White)
            .padding(
                start = 24.dp,
                end = 24.dp,
                top = 16.dp,
                bottom = 24.dp
            )
    ) {
        Column(
            modifier = modifier
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
                mapView = mapView
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
                            text = "서울 서초구 서초중앙로 15",
                            style = MaterialTheme.typography.caption200.copy(
                                fontWeight = FontWeight.Medium,
                                color = Grey700
                            ),
                            modifier = Modifier
                                .wrapContentHeight(align = Alignment.CenterVertically)
                        )
                    }
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(4.dp, Alignment.Start),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
//                    Icon(
//                        painter = painterResource(id = R.drawable.),
//                        contentDescription = ""
//                    )

                        Text(
                            text = "남부터미널역",
                            style = MaterialTheme.typography.caption200.copy(
                                fontWeight = FontWeight.Medium,
                                color = Grey700
                            ),
                            modifier = Modifier
                                .wrapContentHeight(align = Alignment.CenterVertically)
                        )

                        Image(
                            painter = painterResource(id = R.drawable.ic_ellipse),
                            contentDescription = null,
                            modifier = Modifier.align(Alignment.CenterVertically)
                        )

                        Text(
                            text = "5번 출구",
                            style = MaterialTheme.typography.caption200.copy(
                                fontWeight = FontWeight.Normal,
                                color = Grey600
                            ),
                            modifier = Modifier
                                .wrapContentHeight(align = Alignment.CenterVertically)
                        )

                        Text(
                            text = "걸어서 7분",
                            style = MaterialTheme.typography.caption200.copy(
                                fontWeight = FontWeight.Normal,
                                color = Salmon600
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
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
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
    mapView: MapView
) {
    AndroidView(
        {
            mapView
        }, modifier = modifier
            .width(300.dp)
            .height(300.dp)
    )
}


package com.mommydndn.app.ui.features.care.modal

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material3.LocalMinimumInteractiveComponentEnforcement
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderColors
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.SliderPositions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mommydndn.app.R
import com.mommydndn.app.domain.model.care.NearbyNeighborhoodDistance
import com.mommydndn.app.domain.model.user.Neighborhood
import com.mommydndn.app.ui.features.care.NeighborhoodsFilter
import com.mommydndn.app.ui.theme.Grey50
import com.mommydndn.app.ui.theme.Grey500
import com.mommydndn.app.ui.theme.Grey600
import com.mommydndn.app.ui.theme.Grey700
import com.mommydndn.app.ui.theme.Salmon100
import com.mommydndn.app.ui.theme.Salmon200
import com.mommydndn.app.ui.theme.Salmon300
import com.mommydndn.app.ui.theme.Salmon400
import com.mommydndn.app.ui.theme.Salmon600
import com.mommydndn.app.ui.theme.White
import com.mommydndn.app.ui.theme.caption100
import com.mommydndn.app.ui.theme.caption200
import com.mommydndn.app.ui.theme.paragraph400
import com.mommydndn.app.ui.theme.shadow700

@Composable
internal fun NeighborhoodsFilterModalBottomSheet(
    neighborhoodsFilter: NeighborhoodsFilter,
    onCloseClick: () -> Unit,
    onUpdateClick: (NeighborhoodsFilter) -> Unit,
    modifier: Modifier = Modifier,
) {
    val nearbyNeighborhoodDistance = remember {
        mutableStateOf(neighborhoodsFilter.nearbyNeighborhoodDistance)
    }

    val sliderPosition = remember {
        mutableFloatStateOf(nearbyNeighborhoodDistance.value.ordinal.toFloat())
    }

    Box(
        modifier = modifier
            .wrapContentSize()
            .then(shadow700)
            .background(
                color = White,
                shape = RoundedCornerShape(24.dp),
            ),
        contentAlignment = Alignment.TopCenter,
    ) {
        Column(
            modifier = Modifier
                .wrapContentSize()
                .padding(horizontal = 20.dp)
                .padding(top = 10.dp, bottom = 24.dp),
        ) {
            ModalBottomSheetHandleBar(modifier = Modifier.align(Alignment.CenterHorizontally))

            Spacer(modifier = Modifier.height(20.dp))

            val neighborhood = neighborhoodsFilter.neighborhood

            val postfix = when (nearbyNeighborhoodDistance.value) {
                NearbyNeighborhoodDistance.IMMEDIATE -> ""
                NearbyNeighborhoodDistance.NEARBY -> stringResource(
                    R.string.nearby_neighborhoods_count,
                    neighborhood.nearbyNeighborhoods.size
                )
                NearbyNeighborhoodDistance.DISTANT -> stringResource(
                    R.string.nearby_neighborhoods_count,
                    neighborhood.distantNeighborhoods.size
                )
                NearbyNeighborhoodDistance.VERY_DISTANT -> stringResource(
                    R.string.nearby_neighborhoods_count,
                    neighborhood.veryDistantNeighborhoods.size
                )
            }

            NeighborhoodsFilterTitle(
                nearbyNeighborhoods = neighborhood.name + postfix,
                modifier = Modifier.fillMaxWidth()
            )

            ModalBottomSheetHorizontalDivider(
                modifier = Modifier.padding(vertical = 12.dp)
            )

            NearbyNeighborhoodDistance(
                neighborhoodName = neighborhood.name,
                nearbyNeighborhoodDistance = nearbyNeighborhoodDistance.value,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(318F / 168F),
            )

            Spacer(modifier = Modifier.height(12.dp))

            NearbyNeighborhoodDistanceSlider(
                sliderPosition = sliderPosition.floatValue,
                onValueChange = {
                    sliderPosition.floatValue = it
                },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(24.dp))

            ModalBottomSheetButtons(
                onCloseClick = onCloseClick,
                onUpdateClick = {
                    onUpdateClick(
                        neighborhoodsFilter.copy(
                            nearbyNeighborhoodDistance = NearbyNeighborhoodDistance.values()[
                                sliderPosition.floatValue.toInt()
                            ]
                        )
                    )
                },
                modifier = Modifier.fillMaxWidth(),
            )
        }
    }
}

@Composable
private fun NeighborhoodsFilterTitle(
    nearbyNeighborhoods: String,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Text(
            text = stringResource(R.string.nearby_neighborhood_distance),
            textAlign = TextAlign.Start,
            style = MaterialTheme.typography.paragraph400.merge(
                color = Grey700,
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold,
            )
        )

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = nearbyNeighborhoods,
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.caption200.merge(
                    color = Grey600,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Medium,
                )
            )

            Icon(
                painter = painterResource(id = R.drawable.icon_arrow_right),
                contentDescription = "NeighborhoodsFilterTitle_ArrowRight",
                modifier = Modifier.size(24.dp),
                tint = Color.Unspecified,
            )
        }
    }
}

@Composable
private fun NearbyNeighborhoodDistance(
    neighborhoodName: String,
    nearbyNeighborhoodDistance: NearbyNeighborhoodDistance,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .run {
                if (nearbyNeighborhoodDistance == NearbyNeighborhoodDistance.VERY_DISTANT) {
                    border(1.dp, Salmon300, RoundedCornerShape(12.dp))
                } else {
                    this
                }
            }
            .background(
                if (nearbyNeighborhoodDistance == NearbyNeighborhoodDistance.VERY_DISTANT) {
                    Salmon100
                } else {
                    Grey50
                },
                RoundedCornerShape(12.dp),
            )
            .clipToBounds(),
        contentAlignment = Alignment.Center,
    ) {
        Box(
            modifier = Modifier.wrapContentSize(unbounded = true),
            contentAlignment = Alignment.Center,
        ) {
            // todo: size
            if (nearbyNeighborhoodDistance >= NearbyNeighborhoodDistance.DISTANT) {
                Box(
                    modifier = Modifier.size(226.dp).background(Salmon200, CircleShape).run {
                        if (nearbyNeighborhoodDistance == NearbyNeighborhoodDistance.DISTANT) {
                            border(1.dp, Salmon300, CircleShape)
                        } else {
                            this
                        }
                    },
                )
            }

            if (nearbyNeighborhoodDistance >= NearbyNeighborhoodDistance.NEARBY) {
                Box(
                    modifier = Modifier.size(138.dp).background(Salmon100, CircleShape).run {
                        if (nearbyNeighborhoodDistance == NearbyNeighborhoodDistance.NEARBY) {
                            border(1.dp, Salmon300, CircleShape)
                        } else {
                            this
                        }
                    },
                )
            }

            Box(
                modifier = Modifier.size(72.dp).background(Salmon200, CircleShape).run {
                    if (nearbyNeighborhoodDistance == NearbyNeighborhoodDistance.IMMEDIATE) {
                        border(1.dp, Salmon300, CircleShape)
                    } else {
                        this
                    }
                },
            )

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Image(
                    painter = painterResource(id = R.drawable.icon_home_salmon),
                    contentDescription = "NeighborhoodsFilterModalBottomSheet_Home",
                    modifier = Modifier.size(24.dp)
                )

                Text(
                    text = neighborhoodName,
                    style = MaterialTheme.typography.caption100.merge(
                        color = Grey500,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Medium,
                    ),
                )
            }
        }
    }
}

@Composable
private fun NearbyNeighborhoodDistanceSlider(
    sliderPosition: Float,
    onValueChange: (Float) -> Unit,
    modifier: Modifier = Modifier,
    valueRange: ClosedFloatingPointRange<Float> = 0f..3F,
    steps: Int = 2,
    colors: SliderColors = SliderDefaults.colors(
        thumbColor = Salmon600,
        activeTrackColor = Salmon400,
        inactiveTrackColor = Grey50,
        inactiveTickColor = Color.White,
        activeTickColor = Color.White
    ),
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
) {
    Column(
        modifier = modifier.padding(vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        CompositionLocalProvider(LocalMinimumInteractiveComponentEnforcement provides false) {
            Slider(
                value = sliderPosition,
                onValueChange = onValueChange,
                modifier = Modifier.fillMaxWidth(),
                valueRange = valueRange,
                colors = colors,
                interactionSource = interactionSource,
                thumb = {
                    SliderDefaults.Thumb(
                        interactionSource = interactionSource,
                        colors = colors,
                        enabled = true,
                        modifier = Modifier.size(28.dp)
                    )
                },
                track = { sliderPositions ->
                    NearbyNeighborhoodDistanceSliderTrack(
                        sliderPositions = sliderPositions
                    )
                },
                steps = steps,
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = stringResource(R.string.immediate_neighborhood),
                style = MaterialTheme.typography.caption200.merge(
                    color = Grey500,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Medium,
                )
            )

            Text(
                text = stringResource(R.string.very_distant_neighborhood),
                style = MaterialTheme.typography.caption200.merge(
                    color = Grey500,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Medium,
                ),
            )
        }
    }
}

@Composable
private fun NearbyNeighborhoodDistanceSliderTrack(
    sliderPositions: SliderPositions,
    modifier: Modifier = Modifier,
    height: Dp = 8.dp,
    inactiveTrackColor: Color = Grey50,
    activeTrackColor: Color = Salmon400,
    clipShape: Shape = CircleShape,
) {
    BoxWithConstraints(
        modifier = modifier
            .fillMaxWidth()
            .height(height)
            .clip(clipShape)
            .background(inactiveTrackColor),
    ) {
        val fraction = sliderPositions.activeRange.endInclusive - sliderPositions.activeRange.start

        Box(
            modifier = Modifier
                .fillMaxWidth(fraction = fraction)
                .height(height)
                .clip(clipShape)
                .background(activeTrackColor)
        )

        Box(
            modifier = Modifier
                .width(1.5.dp)
                .height(height)
                .absoluteOffset {
                    IntOffset(constraints.maxWidth / 3, 0)
                }
                .background(inactiveTrackColor),
        )

        Box(
            modifier = Modifier
                .width(1.5.dp)
                .height(height)
                .absoluteOffset {
                    IntOffset(constraints.maxWidth * 2 / 3, 0)
                }
                .background(inactiveTrackColor),
        )
    }
}

@Composable
private fun NeighborhoodsFilterModalBottomSheetButtons(
    onCloseClick: () -> Unit,
    onUpdateClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        Button(
            modifier = Modifier.weight(1F),
            elevation = ButtonDefaults.elevation(0.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = Salmon200),
            onClick = onCloseClick,
            contentPadding = PaddingValues(vertical = 16.dp)
        ) {
            Text(
                text = stringResource(R.string.close),
                style = MaterialTheme.typography.paragraph400.merge(
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Medium,
                    color = Salmon600,
                ),
            )
        }

        Button(
            modifier = Modifier.weight(1F),
            elevation = ButtonDefaults.elevation(0.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = Salmon600),
            onClick = onUpdateClick,
            contentPadding = PaddingValues(vertical = 16.dp)
        ) {
            Text(
                text = stringResource(R.string.apply),
                style = MaterialTheme.typography.paragraph400.merge(
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Medium,
                    color = White,
                ),
            )
        }
    }
}

private val emptyNeighborhood = Neighborhood(
    name = "",
    latitude = 0.0,
    longitude = 0.0,
    nearbyNeighborhoods = emptyList(),
    distantNeighborhoods = emptyList(),
    veryDistantNeighborhoods = emptyList()
)

@Preview
@Composable
private fun ImmediateNeighborhoodsFilter() {
    NeighborhoodsFilterModalBottomSheet(
        neighborhoodsFilter = NeighborhoodsFilter(
            neighborhood = Neighborhood(
                name = "방배1동",
                latitude = 0.0,
                longitude = 0.0,
                nearbyNeighborhoods = List(5) { emptyNeighborhood },
                distantNeighborhoods = List(9) { emptyNeighborhood },
                veryDistantNeighborhoods = List(14) { emptyNeighborhood }
            ),
            nearbyNeighborhoodDistance = NearbyNeighborhoodDistance.IMMEDIATE,
        ),
        onCloseClick = {},
        onUpdateClick = {},
    )
}

@Preview
@Composable
private fun NearbyNeighborhoodsFilter() {
    NeighborhoodsFilterModalBottomSheet(
        neighborhoodsFilter = NeighborhoodsFilter(
            neighborhood = Neighborhood(
                name = "방배1동",
                latitude = 0.0,
                longitude = 0.0,
                nearbyNeighborhoods = List(5) { emptyNeighborhood },
                distantNeighborhoods = List(9) { emptyNeighborhood },
                veryDistantNeighborhoods = List(14) { emptyNeighborhood }
            ),
            nearbyNeighborhoodDistance = NearbyNeighborhoodDistance.NEARBY,
        ),
        onCloseClick = {},
        onUpdateClick = {},
    )
}

@Preview
@Composable
private fun DistantNeighborhoodsFilter() {
    NeighborhoodsFilterModalBottomSheet(
        neighborhoodsFilter = NeighborhoodsFilter(
            neighborhood = Neighborhood(
                name = "방배1동",
                latitude = 0.0,
                longitude = 0.0,
                nearbyNeighborhoods = List(5) { emptyNeighborhood },
                distantNeighborhoods = List(9) { emptyNeighborhood },
                veryDistantNeighborhoods = List(14) { emptyNeighborhood }
            ),
            nearbyNeighborhoodDistance = NearbyNeighborhoodDistance.DISTANT,
        ),
        onCloseClick = {},
        onUpdateClick = {},
    )
}

@Preview
@Composable
private fun VeryDistantNeighborhoodsFilter() {
    NeighborhoodsFilterModalBottomSheet(
        neighborhoodsFilter = NeighborhoodsFilter(
            neighborhood = Neighborhood(
                name = "방배1동",
                latitude = 0.0,
                longitude = 0.0,
                nearbyNeighborhoods = List(5) { emptyNeighborhood },
                distantNeighborhoods = List(9) { emptyNeighborhood },
                veryDistantNeighborhoods = List(14) { emptyNeighborhood }
            ),
            nearbyNeighborhoodDistance = NearbyNeighborhoodDistance.VERY_DISTANT,
        ),
        onCloseClick = {},
        onUpdateClick = {},
    )
}
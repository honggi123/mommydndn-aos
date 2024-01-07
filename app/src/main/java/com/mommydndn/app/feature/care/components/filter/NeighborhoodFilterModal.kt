package com.mommydndn.app.feature.care.components.filter

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
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
import androidx.compose.runtime.getValue
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
import com.mommydndn.app.domain.model.user.NearbyNeighborhoods
import com.mommydndn.app.domain.model.user.Neighborhood
import com.mommydndn.app.domain.model.user.NeighborhoodDistance
import com.mommydndn.app.ui.theme.Grey50
import com.mommydndn.app.ui.theme.Grey500
import com.mommydndn.app.ui.theme.Grey600
import com.mommydndn.app.ui.theme.Grey700
import com.mommydndn.app.ui.theme.Salmon100
import com.mommydndn.app.ui.theme.Salmon200
import com.mommydndn.app.ui.theme.Salmon300
import com.mommydndn.app.ui.theme.Salmon400
import com.mommydndn.app.ui.theme.Salmon600
import com.mommydndn.app.ui.theme.caption100
import com.mommydndn.app.ui.theme.caption200
import com.mommydndn.app.ui.theme.paragraph400

data class NeighborhoodFilterUiModel(
    val neighborhoodId: Int,
    val neighborhoodName: String,
    val nearbyNeighborhoodDistance: NeighborhoodDistance,
    val immediateNeighborhoodsCount: Int,
    val nearbyNeighborhoodsCount: Int,
    val distantNeighborhoodsCount: Int,
    val veryDistantNeighborhoodsCount: Int,
)

@Composable
internal fun NeighborhoodFilterModal(
    neighborhood: NeighborhoodFilterUiModel,
    onCloseClick: () -> Unit,
    onApplyClick: (NeighborhoodFilterUiModel) -> Unit,
    modifier: Modifier = Modifier,
) {
    val nearbyNeighborhoodDistance by remember {
        mutableStateOf(neighborhood.nearbyNeighborhoodDistance)
    }

    val sliderPosition = remember {
        mutableFloatStateOf(nearbyNeighborhoodDistance.ordinal.toFloat())
    }

    val nearbyNeighborhoodsCount = when (nearbyNeighborhoodDistance) {
        NeighborhoodDistance.IMMEDIATE -> neighborhood.immediateNeighborhoodsCount
        NeighborhoodDistance.NEARBY -> neighborhood.nearbyNeighborhoodsCount
        NeighborhoodDistance.DISTANT -> neighborhood.distantNeighborhoodsCount
        NeighborhoodDistance.VERY_DISTANT -> neighborhood.veryDistantNeighborhoodsCount
    }

    CareFilterModal(
        onCloseClick = onCloseClick,
        onApplyClick = {
            onApplyClick(
                neighborhood.copy(
                    nearbyNeighborhoodDistance = nearbyNeighborhoodDistance,
                )
            )
        },
        modifier = modifier,
    ) {
        NeighborhoodFilterTitle(
            neighborhoodName = neighborhood.neighborhoodName,
            nearbyNeighborhoodsCount = nearbyNeighborhoodsCount,
            modifier = Modifier.fillMaxWidth(),
        )

        ModalHorizontalDivider(Modifier.padding(vertical = 12.dp))

        NearbyNeighborhoodDistance(
            neighborhoodName = neighborhood.neighborhoodName,
            neighborhoodDistance = nearbyNeighborhoodDistance,
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
    }
}

@Composable
private fun NeighborhoodFilterTitle(
    neighborhoodName: String,
    nearbyNeighborhoodsCount: Int,
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
                text = neighborhoodName + stringResource(
                    id = R.string.nearby_neighborhoods_count,
                    nearbyNeighborhoodsCount
                ),
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
    neighborhoodDistance: NeighborhoodDistance,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .run {
                if (neighborhoodDistance == NeighborhoodDistance.VERY_DISTANT) {
                    border(1.dp, Salmon300, RoundedCornerShape(12.dp))
                } else {
                    this
                }
            }
            .background(
                if (neighborhoodDistance == NeighborhoodDistance.VERY_DISTANT) {
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
            if (neighborhoodDistance >= NeighborhoodDistance.DISTANT) {
                Box(
                    modifier = Modifier
                        .size(226.dp)
                        .background(Salmon200, CircleShape)
                        .run {
                            if (neighborhoodDistance == NeighborhoodDistance.DISTANT) {
                                border(1.dp, Salmon300, CircleShape)
                            } else {
                                this
                            }
                        },
                )
            }

            if (neighborhoodDistance >= NeighborhoodDistance.NEARBY) {
                Box(
                    modifier = Modifier
                        .size(138.dp)
                        .background(Salmon100, CircleShape)
                        .run {
                            if (neighborhoodDistance == NeighborhoodDistance.NEARBY) {
                                border(1.dp, Salmon300, CircleShape)
                            } else {
                                this
                            }
                        },
                )
            }

            Box(
                modifier = Modifier
                    .size(72.dp)
                    .background(Salmon200, CircleShape)
                    .run {
                        if (neighborhoodDistance == NeighborhoodDistance.IMMEDIATE) {
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

private val emptyNeighborhood = Neighborhood(
    id = 0,
    name = "",
    address = "",
    latitude = 0.0,
    longitude = 0.0,
)

private val fakeNeighborhood = Neighborhood(
    id = 0,
    name = "방배동",
    address = "서울 서초구 방배동 135-2",
    latitude = 0.0,
    longitude = 0.0,
    nearbyNeighborhoods = NearbyNeighborhoods(
        nearbyNeighborhoods = buildList(capacity = 5) {
            repeat(5) {
                add(emptyNeighborhood)
            }
        },
        distantNeighborhoods = buildList(capacity = 9) {
            repeat(9) {
                add(emptyNeighborhood)
            }
        },
        veryDistantNeighborhoods = buildList(capacity = 17) {
            repeat(17) {
                add(emptyNeighborhood)
            }
        },
    )
)

private val neighborhoodDummy = NeighborhoodFilterUiModel(
    neighborhoodId = 0,
    neighborhoodName = "방배동",
    nearbyNeighborhoodDistance = NeighborhoodDistance.IMMEDIATE,
    immediateNeighborhoodsCount = 0,
    nearbyNeighborhoodsCount = 5,
    distantNeighborhoodsCount = 9,
    veryDistantNeighborhoodsCount = 17
)

@Preview
@Composable
private fun NeighborhoodFilterModalImmediate() {
    NeighborhoodFilterModal(
        neighborhood = neighborhoodDummy,
        onCloseClick = {},
        onApplyClick = {},
        modifier = Modifier,
    )
}

@Preview
@Composable
private fun NeighborhoodFilterModalNearby() {
    NeighborhoodFilterModal(
        neighborhood = neighborhoodDummy.copy(
            nearbyNeighborhoodDistance = NeighborhoodDistance.NEARBY
        ),
        onCloseClick = {},
        onApplyClick = {},
        modifier = Modifier,
    )
}

@Preview
@Composable
private fun NeighborhoodFilterModalDistant() {
    NeighborhoodFilterModal(
        neighborhood = neighborhoodDummy.copy(
            nearbyNeighborhoodDistance = NeighborhoodDistance.DISTANT
        ),
        onCloseClick = {},
        onApplyClick = {},
        modifier = Modifier,
    )
}

@Preview
@Composable
private fun NeighborhoodFilterModalVeryDistant() {
    NeighborhoodFilterModal(
        neighborhood = neighborhoodDummy.copy(
            nearbyNeighborhoodDistance = NeighborhoodDistance.VERY_DISTANT
        ),
        onCloseClick = {},
        onApplyClick = {},
        modifier = Modifier,
    )
}
package com.mommydndn.app.ui.care.details.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mommydndn.app.R
import com.mommydndn.app.domain.model.CareType
import com.mommydndn.app.ui.care.list.components.displayName
import com.mommydndn.app.ui.theme.Grey700
import com.mommydndn.app.ui.theme.White
import com.mommydndn.app.ui.theme.caption100

@Composable
internal fun DetailsCareTypes(
    careTypes: List<CareType>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(36.dp)
    ) {
        DetailsSectionTitle(title = stringResource(R.string.confidently_help_care_types))

        BoxWithConstraints {
            val cellSize = (maxWidth - 12.dp) / 3

            val height = if (careTypes.size > 3) {
                cellSize * 2 + 6.dp
            } else {
                cellSize
            }

            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                modifier = Modifier.height(height),
                verticalArrangement = Arrangement.spacedBy(6.dp),
                horizontalArrangement = Arrangement.spacedBy(6.dp),
            ) {
                items(careTypes) { careType ->
                    with(careType) {
                        CareTypeGridItem(
                            drawableId = drawableId,
                            name = displayName,
                            modifier = Modifier.size(cellSize),
                        )
                    }
                }
            }
        }
    }
}

private val CareType.drawableId: Int
    @DrawableRes
    get() = when (this) {
        CareType.ChildCare -> R.drawable.icon_baby_bottle
        CareType.SeniorCare -> R.drawable.icon_wheelchair
        CareType.SchoolTransportation -> R.drawable.icon_bus
        CareType.Housekeeping -> R.drawable.icon_tableware
    }

@Composable
private fun CareTypeGridItem(
    drawableId: Int,
    name: String,
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier) {
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxSize()
                .border(1.dp, Color(0xFFFFE7CC), RoundedCornerShape(6.dp)),
            verticalArrangement = Arrangement.spacedBy(2.dp, alignment = Alignment.CenterVertically),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Icon(
                painter = painterResource(id = drawableId),
                contentDescription = null,
                tint = Color.Unspecified,
            )

            Text(
                text = name,
                color = Grey700,
                style = MaterialTheme.typography.caption100
            )
        }
    }
}

@Preview
@Composable
private fun DetailsCareTypesPreview() {
    DetailsCareTypes(
        careTypes = CareType.entries,
        modifier = Modifier
            .background(White)
            .padding(24.dp)
    )
}
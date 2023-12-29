package com.mommydndn.app.feature.care.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.mommydndn.app.R
import com.mommydndn.app.domain.model.user.Neighborhood
import com.mommydndn.app.feature.care.filters.displayName
import com.mommydndn.app.ui.theme.Grey50
import com.mommydndn.app.ui.theme.Grey500
import com.mommydndn.app.ui.theme.Grey600
import com.mommydndn.app.ui.theme.Grey800
import com.mommydndn.app.ui.theme.caption200
import com.mommydndn.app.ui.theme.paragraph300

@Composable
internal fun NeighborhoodPostField(
    neighborhood: Neighborhood,
    onNeighborhoodClick: () -> Unit,
    onNearbyNeighborhoodsClick: () -> Unit,
    modifier: Modifier = Modifier,
    title: String = stringResource(R.string.work_available_neighborhoods),
    subtitle: String = stringResource(id = R.string.required),
) {
    PostField(
        title = title,
        subtitle = subtitle,
        modifier = modifier,
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = stringResource(R.string.my_neighborhood),
                style = MaterialTheme.typography.paragraph300.merge(
                    color = Grey500,
                    fontWeight = FontWeight.Bold,
                )
            )

            Spacer(modifier = Modifier.height(6.dp))

            Row(
                modifier = Modifier
                    .height(56.dp)
                    .background(color = Grey50, shape = RoundedCornerShape(12.dp))
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                        onClick = onNeighborhoodClick,
                    )
                    .padding(start = 20.dp, end = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp), // todo
                verticalAlignment = Alignment.CenterVertically,
            ) {
                // todo: when address is blank
                Text(
                    text = neighborhood.address,
                    modifier = Modifier.weight(1F),
                    style = MaterialTheme.typography.paragraph300.merge(
                        color = Grey800,
                        fontWeight = FontWeight.Normal,
                    )
                )

                Icon(
                    painter = painterResource(id = R.drawable.icon_arrow_down),
                    contentDescription = "PostCareJobOpeningWorkPlace_ArrowDown",
                    modifier = Modifier,
                    tint = Color.Unspecified,
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = neighborhood.displayName(),
                    modifier = Modifier
                        .wrapContentHeight()
                        .clickable { onNearbyNeighborhoodsClick() },
                    style = MaterialTheme.typography.caption200.merge(
                        color = Grey600,
                        fontWeight = FontWeight.Medium,
                    )
                )

                Icon(
                    painter = painterResource(id = R.drawable.icon_arrow_right),
                    contentDescription = "PostCareJobOpeningWorkPlace_ArrowRight",
                    modifier = Modifier.size(24.dp),
                    tint = Color.Unspecified,
                )
            }
        }
    }
}
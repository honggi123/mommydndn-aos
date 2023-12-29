package com.mommydndn.app.ui.features.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.mommydndn.app.data.model.common.BadgeColorType
import com.mommydndn.app.domain.model.care.JobOffer
import com.mommydndn.app.ui.components.common.Badge
import com.mommydndn.app.ui.theme.Grey400
import com.mommydndn.app.ui.theme.Grey50
import com.mommydndn.app.ui.theme.Grey800
import com.mommydndn.app.ui.theme.Shapes
import com.mommydndn.app.ui.theme.caption200
import com.mommydndn.app.ui.theme.paragraph300
import com.mommydndn.app.util.NumberUtils

@Composable
fun JobOfferBox(
    item: JobOffer,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .background(shape = Shapes.large, color = Grey50)
    ) {
        Column(
            modifier = Modifier.padding(start = 20.dp, top = 16.dp, end = 20.dp, bottom = 16.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            Row(modifier = Modifier.fillMaxWidth()) {
                Badge(
                    colorType = BadgeColorType.ORANGE,
                    text = item.caringType.value
                )
                Spacer(modifier = Modifier.padding(8.dp))
                Text(
                    text = item.title,
                    style = MaterialTheme.typography.paragraph300.merge(
                        fontWeight = FontWeight.Bold,
                        color = Grey800
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }

            Text(
                text = item.neighborhood,
                style = MaterialTheme.typography.caption200.merge(
                    fontWeight = FontWeight.Medium,
                    color = Grey400
                )
            )

            Text(
                text = "${item.salaryType.value} ${NumberUtils.getPriceString(item.salary)}",
                style = MaterialTheme.typography.paragraph300.merge(
                    fontWeight = FontWeight.Bold,
                    color = Grey800
                )
            )
        }
    }
}


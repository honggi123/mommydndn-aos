package com.mommydndn.app.ui.components.chip

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Chip
import androidx.compose.material.ChipDefaults
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mommydndn.app.R
import com.mommydndn.app.ui.theme.Grey100
import com.mommydndn.app.ui.theme.Grey400
import com.mommydndn.app.ui.theme.Grey600
import com.mommydndn.app.ui.theme.Grey700
import com.mommydndn.app.ui.theme.Grey800
import com.mommydndn.app.ui.theme.Salmon200
import com.mommydndn.app.ui.theme.Salmon300
import com.mommydndn.app.ui.theme.White
import com.mommydndn.app.ui.theme.caption200

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ChipWithBottomArrow(
    selected: Boolean = true,
    text: String = "",
    onClick: () -> Unit = {}
) {
    when (selected) {
        true -> Box(
            modifier = Modifier
                .background(Salmon200, RoundedCornerShape(20.dp))
                .border(1.dp, Salmon300, RoundedCornerShape(20.dp))
                .clickable {
                    onClick()
                }
        ) {
            Row(
                modifier = Modifier.padding(start = 12.dp, end = 6.dp, top = 6.dp, bottom = 6.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = text,
                    style = MaterialTheme.typography.caption200.copy(
                        fontWeight = FontWeight.Bold,
                        color = Grey800
                    )
                )
                Image(
                    painter = painterResource(id = R.drawable.ic_arrow_down),
                    contentDescription = "",
                    modifier = Modifier
                        .size(24.dp),
                    colorFilter = ColorFilter.tint(Grey600)
                )
            }
        }

        false -> Box(
            modifier = Modifier
                .background(White, RoundedCornerShape(20.dp))
                .border(1.dp, Grey100, RoundedCornerShape(20.dp))
                .clickable { onClick() }
        ) {
            Row(
                modifier = Modifier.padding(start = 12.dp, end = 6.dp, top = 6.dp, bottom = 6.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = text,
                    style = MaterialTheme.typography.caption200.copy(
                        fontWeight = FontWeight.Medium,
                        color = Grey700
                    )
                )
                Image(
                    painter = painterResource(id = R.drawable.ic_arrow_down),
                    contentDescription = "",
                    modifier = Modifier
                        .size(24.dp),
                    colorFilter = ColorFilter.tint(Grey400)
                )
            }
        }
    }

}

@Preview
@Composable
fun MyChipWithArrowIconPreview() {
    ChipWithBottomArrow(
        selected = false,
        text = "텍스트"
    )
}
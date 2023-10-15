package com.mommydndn.app.ui.components.chip

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
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
import com.mommydndn.app.ui.theme.Grey600
import com.mommydndn.app.ui.theme.Grey700
import com.mommydndn.app.ui.theme.Grey800
import com.mommydndn.app.ui.theme.Salmon200
import com.mommydndn.app.ui.theme.Salmon300
import com.mommydndn.app.ui.theme.caption200

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ChipWithBottomArrow(
    selected: Boolean = true,
    text: String = "",
    onClick: () -> Unit = {}
) {
    when (selected) {
        true -> Chip(
            colors = ChipDefaults.chipColors(
                backgroundColor = Salmon200,
            ),
            border = BorderStroke(1.dp, Salmon300),
            onClick = {
                onClick()
            },
            content = {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text(
                        text = text,
                        style = MaterialTheme.typography.caption200.copy(
                            fontWeight = FontWeight.Bold,
                            color = Grey800,
                            platformStyle = PlatformTextStyle(
                                includeFontPadding = false
                            )
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
            })

        false -> Chip(
            onClick = {
                onClick()
            },
            content = {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = text,
                        style = MaterialTheme.typography.caption200.copy(
                            fontWeight = FontWeight.Medium,
                            color = Grey700,
                            platformStyle = PlatformTextStyle(
                                includeFontPadding = false
                            )
                        )
                    )
                    Image(
                        painter = painterResource(id = R.drawable.ic_arrow_down),
                        contentDescription = "",
                        modifier = Modifier
                            .size(24.dp)
                    )
                }
            })
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
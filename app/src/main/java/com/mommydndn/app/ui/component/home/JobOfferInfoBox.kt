package com.mommydndn.app.ui.component.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mommydndn.app.data.model.BannerColorType
import com.mommydndn.app.ui.theme.Grey400
import com.mommydndn.app.ui.theme.Grey50
import com.mommydndn.app.ui.theme.Grey800
import com.mommydndn.app.ui.theme.MommydndnaosTheme
import com.mommydndn.app.ui.theme.Shapes
import com.mommydndn.app.ui.theme.caption200
import com.mommydndn.app.ui.theme.paragraph300

@Composable
fun JobOfferInfoBox(
    caringText: String = "",
    locationText: String = "",
    salaryText: String = ""
) {
    Box(
        modifier = Modifier.background(shape = Shapes.large, color = Grey50)
    ) {
        Column(
            modifier = Modifier.padding(start = 20.dp, top = 16.dp, end = 20.dp, bottom = 16.dp)
        ) {
            Row(modifier = Modifier.fillMaxWidth()) {
                Badge(colorType = BannerColorType.ORANGE, text = caringText)
                Spacer(modifier = Modifier.padding(6.dp))
                Text(
                    text = caringText,
                    style = MaterialTheme.typography.paragraph300.copy(
                        fontWeight = FontWeight.Bold,
                        color = Grey800,
                        platformStyle = PlatformTextStyle(
                            includeFontPadding = false
                        )
                    )
                )
            }
            Spacer(modifier = Modifier.padding(24.dp))
            Text(
                text = locationText,
                style = MaterialTheme.typography.caption200.copy(
                    fontWeight = FontWeight.Medium,
                    color = Grey400,
                    platformStyle = PlatformTextStyle(
                        includeFontPadding = false
                    )
                )
            )
            Text(
                text = salaryText,
                style = MaterialTheme.typography.paragraph300.copy(
                    fontWeight = FontWeight.Bold,
                    color = Grey800,
                    platformStyle = PlatformTextStyle(
                        includeFontPadding = false
                    )
                )
            )
        }
    }
}

@Preview
@Composable
fun previewJobOfferInfoBox() {
    MommydndnaosTheme {
        JobOfferInfoBox(
            caringText = "요양",
            locationText = "반포동",
            salaryText = "0"
        )
    }
}
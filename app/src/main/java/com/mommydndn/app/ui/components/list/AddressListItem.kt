package com.mommydndn.app.ui.components.list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mommydndn.app.ui.theme.Grey500
import com.mommydndn.app.ui.theme.Grey700
import com.mommydndn.app.ui.theme.caption200
import com.mommydndn.app.ui.theme.paragraph300

@Composable
fun AddressListItem(
    modifier: Modifier = Modifier,
    streetNum: Int,
    roadAddressText: String = "",
    streetAddressText: String = ""
) {
    Box(
        modifier = modifier
            .clip(shape = RoundedCornerShape(12.dp))
            .background(color = Color.White)
            .padding(
                horizontal = 12.dp,
                vertical = 8.dp
            )
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(2.dp, Alignment.CenterVertically),
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.Start),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(32.dp)
            ) {
                Text(
                    text = streetNum.toString(),
                    style = MaterialTheme.typography.paragraph300.copy(
                        fontWeight = FontWeight.Normal,
                        color = Grey700,
                        textAlign = TextAlign.Center
                    )
                )
            }
            Row(
                horizontalArrangement = Arrangement.spacedBy(6.dp, Alignment.Start),
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                // TODO
                /*
TagLabel(
                    colorType = BadgeColorType.ORANGE,
                    text = "도로명"
                )
                 */

                Text(
                    text = roadAddressText,
                    style = MaterialTheme.typography.caption200.copy(
                        fontWeight = FontWeight.Normal,
                        color = Grey500
                    )
                )
            }
            Row(
                horizontalArrangement = Arrangement.spacedBy(6.dp, Alignment.Start),
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                // TODO
                /*
                TagLabel(
                    modifier = Modifier.width(47.dp),
                    colorType = BadgeColorType.ORANGE,
                    text = "지 번"
                )
                 */

                Text(
                    text = streetAddressText,
                    style = MaterialTheme.typography.caption200.copy(
                        fontWeight = FontWeight.Normal,
                        color = Grey500
                    )
                )
            }
        }
    }
}


@Preview(widthDp = 342, heightDp = 128)
@Composable
private fun AddressListItemPreview() {
    AddressListItem(
        Modifier,
        0,
        "서울특별시 서초구 강남대로 321 (서초동, 서초동대우디오빌프라임)",
        "서울특별시 서초구 서초동 1337-22 서초동 대우디오빌프라임"
    )
}
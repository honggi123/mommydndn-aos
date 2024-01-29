package com.mommydndn.app.ui.components.address

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.SubcomposeLayout
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import com.mommydndn.app.R
import com.mommydndn.app.ui.components.tag.TagLabel
import com.mommydndn.app.ui.theme.DeepOrange
import com.mommydndn.app.ui.theme.Grey500
import com.mommydndn.app.ui.theme.Grey700
import com.mommydndn.app.ui.theme.Orange100
import com.mommydndn.app.ui.theme.caption200
import com.mommydndn.app.ui.theme.paragraph300

@Composable
fun AddressListItem(
    postalCode: Int,
    streetAddress: String,
    landLotAddress: String,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(shape = RoundedCornerShape(12.dp))
                .background(color = Color.White)
                .padding(horizontal = 12.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(2.dp),
        ) {
            Text(
                text = postalCode.toString(),
                modifier = Modifier.padding(vertical = 4.dp),
                color = Grey700,
                style = MaterialTheme.typography.paragraph300.merge(
                    fontWeight = FontWeight.Normal,
                )
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(6.dp),
            ) {
                TagLabel(
                    text = stringResource(R.string.street_address),
                    textColor = DeepOrange,
                    backgroundColor = Orange100,
                )

                Text(
                    text = streetAddress,
                    modifier = Modifier.weight(1F),
                    color = Grey500,
                    style = MaterialTheme.typography.caption200.merge(
                        fontWeight = FontWeight.Normal,
                    )
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(6.dp),
            ) {
                Box(Modifier.width(IntrinsicSize.Max)) {
                    TagLabel(
                        text = stringResource(R.string.street_address),
                        textColor = DeepOrange,
                        backgroundColor = Orange100,
                        modifier = Modifier.alpha(0F),
                    )

                    TagLabel(
                        text = stringResource(R.string.land_lot_address),
                        textColor = DeepOrange,
                        backgroundColor = Orange100,
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                Text(
                    text = landLotAddress,
                    modifier = Modifier.weight(1F),
                    color = Grey500,
                    style = MaterialTheme.typography.caption200.copy(
                        fontWeight = FontWeight.Normal,

                        )
                )
            }
        }
    }
}

@Composable
private fun AdressSubcomposeLayout(
    streetAddressTag: @Composable () -> Unit,
    streetAddress: @Composable () -> Unit,
    landLotAddressTag: @Composable () -> Unit,
    landLotAddress: @Composable () -> Unit,
) {
    SubcomposeLayout { constraints ->
        val streetAddressTagPlaceable = subcompose(0, streetAddressTag).map {
            it.measure(Constraints())
        }

        val landLotAddressTagPlaceable = subcompose(2, landLotAddressTag).map {
            it.measure(Constraints())
        }

        val streetAddressPlaceable = subcompose(1, streetAddress).map {
            it.measure(Constraints())
        }

        val landLotAddressPlaceable = subcompose(3, landLotAddress).map {
            it.measure(Constraints())
        }

        TODO()
    }
}

@Preview
@Composable
private fun AddressListItemPreview() {
    AddressListItem(
        postalCode = 53333,
        streetAddress = "서울특별시 서초구 강남대로 321 (서초동, 서초동대우디오빌프라임)",
        landLotAddress = "서울특별시 서초구 서초동 1337-22 서초동 대우디오빌프라임",
        modifier = Modifier,
    )
}
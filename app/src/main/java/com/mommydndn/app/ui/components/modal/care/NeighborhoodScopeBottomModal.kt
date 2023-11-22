package com.mommydndn.app.ui.components.modal.care

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Slider
import androidx.compose.material.SliderDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.mommydndn.app.data.model.care.Filter.FilterItemsType
import com.mommydndn.app.ui.components.modal.components.DialogButtonsRow
import com.mommydndn.app.ui.components.modal.components.DialogTitleWrapper
import com.mommydndn.app.ui.models.dialog.DialogButton
import com.mommydndn.app.ui.models.dialog.DialogTitle
import com.mommydndn.app.ui.theme.Grey200
import com.mommydndn.app.ui.theme.Grey50
import com.mommydndn.app.ui.theme.Salmon300
import com.mommydndn.app.ui.theme.Salmon400
import com.mommydndn.app.ui.theme.Salmon600
import com.mommydndn.app.ui.theme.White
import com.mommydndn.app.ui.theme.shadow700
import com.mommydndn.app.R
import com.mommydndn.app.data.model.care.DistanceType
import com.mommydndn.app.ui.theme.Grey500
import com.mommydndn.app.ui.theme.caption200

@Composable
fun NeighborhoodScopeBottomModal(
    modifier: Modifier = Modifier,
    item: FilterItemsType.NeighborhoodScope,
    onClickClose: () -> Unit = {},
    onClickComplete: (FilterItemsType.NeighborhoodScope) -> Unit = {}
) {
    val scopeItem by remember { mutableStateOf(item) }

    var selectedDistanceType by remember {
        mutableStateOf(scopeItem.list.filter { it.isSelected }.first())
    }
    var sliderPosition by remember { mutableStateOf(selectedDistanceType.distantceType.distantce.toFloat()) }

    Box(
        modifier = modifier
            .wrapContentSize()
            .then(shadow700)
            .background(color = White, shape = RoundedCornerShape(24.dp)),
        contentAlignment = Alignment.TopCenter
    ) {

        Box(
            modifier = Modifier
                .offset(y = 10.dp)
                .width(64.dp)
                .height(6.dp)
                .background(color = Grey200, shape = RoundedCornerShape(size = 50.dp))
        )

        Column(
            modifier = Modifier
                .wrapContentSize()
                .padding(start = 20.dp, top = 36.dp, end = 20.dp, bottom = 24.dp),
        ) {
            DialogTitleWrapper(
                DialogTitle.Location(
                    text = "동네 범위",
                    locationText = "${scopeItem.myLocationName} 외 근처 동네 ${DistanceType.find(sliderPosition.toInt()).distantce}개",
                    action = {}
                )
            )

            Divider(
                thickness = 1.5.dp,
                color = Grey50,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Transparent)
                    .padding(vertical = 12.dp)
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(168.dp)
                    .border(1.dp, Salmon300, RoundedCornerShape(12.dp))
                    .background(Grey50, RoundedCornerShape(12.dp)),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_home_salmon),
                    contentDescription = "",
                    modifier = Modifier.size(24.dp)
                )
            }

            Slider(
                modifier = Modifier
                    .height(100.dp)
                    .padding(top = 32.dp),
                value = sliderPosition,
                onValueChange = {
                    sliderPosition = it
                },
                valueRange = 0f..24f,
                onValueChangeFinished = {},
                steps = 2,
                colors = SliderDefaults.colors(
                    thumbColor = Salmon600,
                    activeTrackColor = Salmon400,
                    inactiveTrackColor = Grey50,
                    inactiveTickColor = Color.White,
                    activeTickColor = Color.White
                )
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "내 동네",
                    style = MaterialTheme.typography.caption200.copy(
                        fontWeight = FontWeight.Medium,
                        color = Grey500
                    )
                )
                Text(
                    text = "먼 동네",
                    style = MaterialTheme.typography.caption200.copy(
                        fontWeight = FontWeight.Medium,
                        color = Grey500
                    )
                )
            }

            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(28.dp)
            )

            DialogButtonsRow(
                listOf(
                    DialogButton.Secondary(title = "닫기", action = { onClickClose() }),
                    DialogButton.Primary(title = "적용하기", action = {
                        onClickComplete(
                            FilterItemsType.NeighborhoodScope(
                                myLocationName = scopeItem.myLocationName,
                                list = scopeItem.list.map { item ->
                                    if (item.distantceType == DistanceType.find(sliderPosition.toInt())) {
                                        item.copy(isSelected = true)
                                    } else item
                                }
                            )
                        )
                    })
                )
            )
        }
    }
}


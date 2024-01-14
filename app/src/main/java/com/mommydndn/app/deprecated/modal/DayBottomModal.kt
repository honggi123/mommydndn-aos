package com.mommydndn.app.deprecated.modal

import androidx.compose.foundation.background
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
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.mommydndn.app.data.model.care.filter.FilterItemsType
import com.mommydndn.app.data.model.common.SelectButtonContent
import com.mommydndn.app.deprecated.components.button.SelectButton
import com.mommydndn.app.deprecated.components.modal.components.DialogButtonsRow
import com.mommydndn.app.deprecated.components.modal.components.DialogTitleWrapper
import com.mommydndn.app.deprecated.models.dialog.DialogButton
import com.mommydndn.app.deprecated.models.dialog.DialogTitle
import com.mommydndn.app.ui.theme.Grey200
import com.mommydndn.app.ui.theme.Grey50
import com.mommydndn.app.ui.theme.White
import com.mommydndn.app.ui.theme.Shadow700
import kotlinx.coroutines.CoroutineScope

@Composable
fun DayBottomModal(
    modifier: Modifier = Modifier,
    item: FilterItemsType.Day,
    onClickClose: () -> Unit = {},
    onClickComplete: (FilterItemsType.Day) -> Unit = {},
    scaffoldState: ScaffoldState
) {
    var dayItemList by rememberSaveable(Unit) { mutableStateOf(item.list) }

    val coroutineScope: CoroutineScope = rememberCoroutineScope()

    Box(
        modifier = modifier
            .wrapContentSize()
            .then(Shadow700)
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
            DialogTitleWrapper(DialogTitle.Refresh(text = "시간", refreshAction = {
                dayItemList = dayItemList.toMutableList().map {
                    it.copy(isSelected = false)
                }
            }))

            Divider(
                thickness = 1.5.dp,
                color = Grey50,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Transparent)
                    .padding(vertical = 12.dp)
            )

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 4.dp, vertical = 12.dp)
            ) {
                dayItemList.forEachIndexed { index, item ->
                    SelectButton(
                        modifier = Modifier.size(36.dp),
                        content = SelectButtonContent(
                            isSelected = item.isSelected,
                            onClick = {
                                dayItemList = dayItemList.toMutableList().also {
                                    it[index] = it[index].copy(isSelected = !it[index].isSelected)
                                }
                            },
                            text = item.type.displayingName
                        )
                    )
                }
            }

            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(28.dp)
            )

            DialogButtonsRow(
                listOf(
                    DialogButton.Secondary(title = "닫기", action = {
                        onClickClose()
                        dayItemList = item.list
                    }),
                    DialogButton.Primary(
                        title = "적용하기",
                        action = {
                            onClickComplete(FilterItemsType.Day(dayItemList))
                        })
                )
            )
        }
    }
}


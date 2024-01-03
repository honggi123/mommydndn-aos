package com.mommydndn.app.ui.deprecated.modal

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.mommydndn.app.data.model.care.filter.FilterItemsType
import com.mommydndn.app.ui.deprecated.components.inputfield.RadioListItem
import com.mommydndn.app.ui.deprecated.components.modal.components.DialogButtonsRow
import com.mommydndn.app.ui.deprecated.components.modal.components.DialogTitleWrapper
import com.mommydndn.app.ui.deprecated.models.dialog.DialogButton
import com.mommydndn.app.ui.deprecated.models.dialog.DialogTitle
import com.mommydndn.app.ui.theme.Grey200
import com.mommydndn.app.ui.theme.Grey50
import com.mommydndn.app.ui.theme.White
import com.mommydndn.app.ui.theme.shadow700


@Composable
fun PeriodBottomModal(
    modifier: Modifier = Modifier,
    item: FilterItemsType.Period,
    onClickClose: () -> Unit = {},
    onClickComplete: (FilterItemsType.Period) -> Unit = {}
) {
    var periodItemList by rememberSaveable(Unit) { mutableStateOf(item.list) }

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
            DialogTitleWrapper(DialogTitle.Default(text = "돌봄 주기"))

            Divider(
                thickness = 1.5.dp,
                color = Grey50,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Transparent)
                    .padding(vertical = 12.dp)
            )

            Column {
                periodItemList.forEachIndexed { index, periodType ->
                    RadioListItem(
                        checked = periodType.isSelected,
                        onCheckedChange = {
                            periodItemList = periodItemList.toMutableList().also {
                                it.forEach { it.isSelected = false }
                                it[index] = it[index].copy(isSelected = true)
                            }
                        },
                        text = periodType.workPeriodType?.value ?: "전체"
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
                        periodItemList = item.list
                    }),
                    DialogButton.Primary(title = "적용하기", action = {
                        onClickComplete(
                            FilterItemsType.Period(list = periodItemList)
                        )
                    })
                )
            )
        }
    }
}

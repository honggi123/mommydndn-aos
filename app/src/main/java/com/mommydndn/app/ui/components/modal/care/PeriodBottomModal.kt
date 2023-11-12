package com.mommydndn.app.ui.components.modal.care

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.mommydndn.app.data.model.care.Filter.FilterItemsType
import com.mommydndn.app.ui.components.common.RadioListItem
import com.mommydndn.app.ui.components.modal.components.DialogButtonsRow
import com.mommydndn.app.ui.components.modal.components.DialogTitleWrapper
import com.mommydndn.app.ui.models.dialog.DialogButton
import com.mommydndn.app.ui.models.dialog.DialogTitle
import com.mommydndn.app.ui.theme.Grey50
import com.mommydndn.app.ui.theme.White
import com.mommydndn.app.ui.theme.shadow700


@Composable
fun PeriodBottomModal(
    modifier: Modifier = Modifier,
    item: FilterItemsType.Period
) {
    val periodItem by remember { mutableStateOf(item) }

    Box(
        modifier = modifier
            .wrapContentSize()
            .then(shadow700)
            .background(color = White, shape = RoundedCornerShape(24.dp)),
        contentAlignment = Alignment.TopCenter
    ) {

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
                periodItem.list.forEachIndexed { index, periodType ->
                    RadioListItem(
                        checked = periodType.isSelected,
                        onCheckedChange = {
                            periodType.isSelected = !periodType.isSelected
                        },
                        text = periodType.value
                    )
                }
            }
            
            Spacer(modifier = Modifier.fillMaxWidth().height(28.dp))

            DialogButtonsRow(
                listOf(
                    DialogButton.Secondary(title = "닫기", action = {}),
                    DialogButton.Primary(title = "적용하기", action = {})
                )
            )
        }
    }
}

package com.mommydndn.app.deprecated.signup.component

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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mommydndn.app.R
import com.mommydndn.app.data.model.common.ButtonColor
import com.mommydndn.app.data.model.common.ButtonColorType
import com.mommydndn.app.data.model.common.ButtonSizeType
import com.mommydndn.app.domain.model.tos.TermsOfService
import com.mommydndn.app.deprecated.components.button.MommyDndnButton
import com.mommydndn.app.deprecated.components.list.CheckBoxListItem
import com.mommydndn.app.deprecated.components.list.CheckMarkListItem
import com.mommydndn.app.ui.theme.Grey200
import com.mommydndn.app.ui.theme.Grey50
import com.mommydndn.app.ui.theme.White
import com.mommydndn.app.ui.theme.Shadow700

@Composable
fun TosCheckListModal(
    checkBoxTitle: String,
    itemList: List<TermsOfService>,
    onDismiss: () -> Unit,
    onComplete: (List<TermsOfService>) -> Unit,
    checkedStates: List<Boolean>,
    onCheckedChange: (Int, Boolean) -> Unit,
    isAllChecked: Boolean,
    onIsAllCheckedChange: (Boolean) -> Unit,
    isNextButtonEnabled: Boolean,
    modifier: Modifier = Modifier
) {
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
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            CheckBoxListItem(
                checked = isAllChecked,
                onCheckedChange = { isChecked ->
                    itemList.onEachIndexed{ index, item ->
                        onCheckedChange(index, isChecked)
                    }
                    onIsAllCheckedChange(isChecked)
                },
                text = checkBoxTitle
            )

            Spacer(modifier = Modifier.size(12.dp))

            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.5.dp)
                    .background(Grey50)
            )

            Spacer(modifier = Modifier.size(12.dp))

            itemList.onEachIndexed { index, item ->
                CheckMarkListItem(
                    checked = checkedStates[index],
                    onCheckedChange = { isChecked ->
                        onCheckedChange(index, isChecked)
                    }, text = itemList[index].name
                )
                if (index < itemList.size - 1) {
                    Spacer(modifier = Modifier.size(6.dp))
                }
            }

            Spacer(modifier = Modifier.size(28.dp))

            Row(modifier = Modifier.fillMaxWidth()) {
                MommyDndnButton(
                    color = ButtonColor.SALMON,
                    colorType = ButtonColorType.WEAK,
                    sizeType = ButtonSizeType.LARGE,
                    onClick = { onDismiss() },
                    text = stringResource(id = R.string.close)
                )
                Spacer(modifier = Modifier.size(12.dp))
                MommyDndnButton(
                    color = ButtonColor.SALMON,
                    colorType = ButtonColorType.FILLED,
                    sizeType = ButtonSizeType.LARGE,
                    onClick = {
                        val checkedList = itemList.filterIndexed { index, _ ->
                            checkedStates.getOrNull(index) == true
                        }

                        onComplete(checkedList)
                    },
                    text = stringResource(id = R.string.move_on),
                    enabled = isNextButtonEnabled
                )
            }
        }
    }
}

@Preview
@Composable
private fun Tos_Preview() {
    TosCheckListModal(
        checkBoxTitle = "",
        itemList = listOf(),
        onDismiss = {},
        onComplete = {},
        checkedStates = listOf(),
        onCheckedChange = { i: Int, b: Boolean -> },
        isAllChecked = false,
        onIsAllCheckedChange = {},
        isNextButtonEnabled = false,
        modifier = Modifier
    )
}


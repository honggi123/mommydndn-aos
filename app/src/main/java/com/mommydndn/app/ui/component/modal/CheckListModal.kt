package com.mommydndn.app.ui.component.modal

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
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.mommydndn.app.data.model.TermsItem
import com.mommydndn.app.ui.component.Item.CheckBoxListItem
import com.mommydndn.app.ui.component.Item.CheckMarkListItem
import com.mommydndn.app.ui.theme.Grey200
import com.mommydndn.app.ui.theme.Grey50
import com.mommydndn.app.ui.theme.White
import com.mommydndn.app.ui.theme.shadow700

@Composable
fun CheckListModal(
    modifier: Modifier = Modifier,
    contentList: List<TermsItem>,
    closeAction: () -> Unit,
    completeAction: () -> Unit
) {
    var checkedStates by remember { mutableStateOf(List(contentList.size) { false }) }
    val (isAllChecked, setIsAllChecked) = remember { mutableStateOf(false) }

    if (checkedStates.size != contentList.size) {
        checkedStates = List(contentList.size) { false }
    }

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
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            CheckBoxListItem(
                checked = isAllChecked,
                onCheckedChange = { checked ->
                    setIsAllChecked(checked)
                    checkedStates = List(contentList.size) { checked }
                },
                text = "모두 동의해요"
            )

            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.5.dp)
                    .background(Grey50)
            )

            contentList.onEachIndexed { index, item ->
                CheckMarkListItem(
                    checked = checkedStates[index],
                    onCheckedChange = { isChecked ->
                        checkedStates = checkedStates.toMutableList().apply {
                            this[index] = isChecked
                        }
                    }, text = item.name
                )
            }

            Spacer(modifier = Modifier.size(28.dp))
            Row(modifier = Modifier.fillMaxWidth()) {
                Button(modifier = Modifier.weight(1f), onClick = { closeAction() }) {
                    Text(text = "닫기", color = Color.Black)
                }
                Spacer(modifier = Modifier.size(12.dp))
                Button(modifier = Modifier.weight(1f), onClick = { completeAction() }) {
                    Text(text = "다음으로", color = Color.Black)
                }
            }
        }

    }
}



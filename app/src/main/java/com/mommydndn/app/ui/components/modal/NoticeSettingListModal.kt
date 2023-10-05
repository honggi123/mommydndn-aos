package com.mommydndn.app.ui.components.modal

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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.mommydndn.app.data.model.NoticeSetting
import com.mommydndn.app.ui.components.common.CheckBoxListItem
import com.mommydndn.app.ui.components.common.CheckMarkListItem
import com.mommydndn.app.ui.theme.Grey200
import com.mommydndn.app.ui.theme.Grey50
import com.mommydndn.app.ui.theme.White
import com.mommydndn.app.ui.theme.shadow700

@Composable
fun NoticeSettingListModal(
    modifier: Modifier = Modifier,
    titleCheckBoxText: String = "",
    itemList: List<NoticeSetting>,

    onItemSelected: (Int, Boolean) -> Unit,
    onDismiss: () -> Unit,
    onComplete: () -> Unit,
) {

    val (isAllChecked, setIsAllChecked) = remember { mutableStateOf(false) }


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
                    itemList.onEachIndexed { index, _ ->
                        setIsAllChecked(checked)
                        onItemSelected(index, checked)
                    }
                },
                text = titleCheckBoxText
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
                    checked = itemList[index].isSelected,
                    onCheckedChange = { isChecked ->
                        onItemSelected(index, isChecked)
                    }, text = itemList[index].noticeTypeName
                )
                if (index < itemList.size - 1) {
                    Spacer(modifier = Modifier.size(6.dp))
                }
            }

            Spacer(modifier = Modifier.size(28.dp))

            Row(modifier = Modifier.fillMaxWidth()) {
                Button(modifier = Modifier.weight(1f), onClick = { onDismiss() }) {
                    Text(text = "닫기", color = Color.Black)
                }
                Spacer(modifier = Modifier.size(12.dp))
                Button(
                    modifier = Modifier.weight(1f),
                    onClick = { onComplete() },
                ) {
                    Text(text = "알림 받고 시작하기", color = Color.Black)
                }
            }
        }

    }
}


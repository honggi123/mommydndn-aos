package com.mommydndn.app.ui.components.box

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemsIndexed
import com.mommydndn.app.ui.components.common.RadioListItem
import com.mommydndn.app.ui.theme.Grey400
import com.mommydndn.app.ui.theme.White
import com.mommydndn.app.ui.theme.paragraph300

@Composable
fun <T : Any> RadioListBox(
    modifier: Modifier = Modifier,
    pagingItems: LazyPagingItems<T>,
    onItemClick: (T?) -> Unit,
    itemNameDisplay: (T) -> String
) {
    var checkedStates by remember { mutableStateOf(List(pagingItems.itemCount) { false }) }

    if (checkedStates.size != pagingItems.itemCount) {
        checkedStates = List(pagingItems.itemCount) { false }
    }

    if (pagingItems.itemCount == 0) {
        Box(
            modifier = modifier
                .fillMaxWidth()
                .height(584.dp)
                .background(White),
        ) {
            Text(
                modifier = Modifier.padding(start = 97.5.dp, top = 96.dp),
                text = "검색 결과가 없어요." +
                        "\n동네 이름을 다시 확인해주세요!",
                style = MaterialTheme.typography.paragraph300.copy(
                    fontWeight = FontWeight.Normal,
                    color = Grey400,
                    textAlign = TextAlign.Center
                )
            )
        }
    } else {
        LazyColumn(
            modifier = Modifier
                .width(390.dp)
                .height(584.dp)
                .padding(top = 6.dp)
                .background(White),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            itemsIndexed(pagingItems) { index, item ->
                RadioListItem(
                    modifier = Modifier.padding(bottom = 6.dp),
                    checked = checkedStates[index],
                    onCheckedChange = { isChecked ->

                        checkedStates = List(pagingItems.itemCount) { false }

                        checkedStates = checkedStates.toMutableList().apply {
                            this[index] = isChecked
                        }

                        if (isChecked) {
                            onItemClick(item)
                        }
                    },
                    text = item?.let { itemNameDisplay(it) } ?: ""
                )
            }
        }
    }

}

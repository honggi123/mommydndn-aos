package com.mommydndn.app.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mommydndn.app.ui.theme.Grey400
import com.mommydndn.app.ui.theme.White
import com.mommydndn.app.ui.theme.paragraph300

@Composable
fun ListBox(
    items: List<String>
) {
    if (items.isEmpty()) {
        Box(
            modifier = Modifier
                .width(390.dp)
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
                    platformStyle = PlatformTextStyle(
                        includeFontPadding = false
                    ),
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
            items(items) { item ->
                ListItem(
                    modifier = Modifier.padding(bottom = 6.dp),
                    checked = false,
                    onCheckedChange = {},
                    text = item
                )
            }
        }
    }

}

@Preview
@Composable
fun PreviewListBox() {
    val stringList = mutableListOf<String>()
    ListBox(items = stringList)
}
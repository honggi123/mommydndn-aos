package com.mommydndn.app.ui.components.box

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mommydndn.app.R
import com.mommydndn.app.data.model.common.ImageInputFieldType
import com.mommydndn.app.ui.components.chip.LabelChip
import com.mommydndn.app.ui.components.common.ImageInputField
import com.mommydndn.app.ui.theme.Grey500
import com.mommydndn.app.ui.theme.Grey800
import com.mommydndn.app.ui.theme.White
import com.mommydndn.app.ui.theme.caption100
import com.mommydndn.app.ui.theme.paragraph300
import com.mommydndn.app.utils.PermissionUtils

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ContentBox(
    modifier: Modifier = Modifier,
    infos: List<String> = emptyList(),
    photos: List<Uri> = emptyList()
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(28.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.End,
        modifier = modifier
            .requiredWidth(width = 342.dp)
            .clip(shape = RoundedCornerShape(6.dp))
            .background(color = White)
            .padding(
                horizontal = 6.dp,
                vertical = 8.dp
            )
    ) {
        Text(
            text = "안녕하세요 ~ \n6세 여아와 4세 남아를 키우는 워킹맘입니다!\n\n이번 주말 2일간 출장을 가게 되어 시터 선생님을 구하게 되었어요~ 1일 당 8시간, 2일 간 맡아주실 분을 구하고 있습니다!\n\n시급은 일단 12,000원으로 올려놓았지만 \n선생님 경력에 따라 협의도 가능합니다 ㅎㅎ \n\n아무래도 두 아이인 만큼 육아 난이도가 있다고 생각하기에, 고수엄마이신 분이 지원해주시면 감사드리겠습니다 :) ~\n\n부담없이 많은 지원 부탁드려요! ",
            color = Grey800,
            style = MaterialTheme.typography.paragraph300.copy(
                fontWeight = FontWeight.Normal
            ),
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(align = Alignment.CenterVertically)
        )
        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(6.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp),
        ) {
            infos.forEach { chipText ->
                LabelChip(text = chipText)
            }
        }
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(max = 700.dp)
                .wrapContentHeight(),
            verticalArrangement = Arrangement.spacedBy(3.dp),
            horizontalArrangement = Arrangement.spacedBy(3.dp)
        ) {
            items(photos) { uri ->
                ImageInputField(
                    inputType = ImageInputFieldType.Ineditable(
                        imageUri = uri,
                    )
                )
            }
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(4.dp, Alignment.Start),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "지원자 3명",
                color = Grey500,
                style = MaterialTheme.typography.caption100.copy(
                    fontWeight = FontWeight.Normal
                ),
                modifier = Modifier
                    .wrapContentHeight(align = Alignment.CenterVertically)
            )
            Image(
                painter = painterResource(id = R.drawable.ic_ellipse),
                contentDescription = null,
                modifier = Modifier.align(Alignment.CenterVertically)
            )
            Text(
                text = "관심 34명",
                color = Grey500,
                style = MaterialTheme.typography.caption100.copy(
                    fontWeight = FontWeight.Normal
                ),
                modifier = Modifier
                    .wrapContentHeight(align = Alignment.CenterVertically)
            )
            Image(
                painter = painterResource(id = R.drawable.ic_ellipse),
                contentDescription = null,
                modifier = Modifier.align(Alignment.CenterVertically)
            )
            Text(
                text = "조회 230회",
                color = Grey500,
                style = MaterialTheme.typography.caption100.copy(
                    fontWeight = FontWeight.Normal
                ),
                modifier = Modifier
                    .wrapContentHeight(align = Alignment.CenterVertically)
            )
        }
    }
}

@Preview
@Composable
fun previewContentBox() {
    ContentBox(infos = listOf("a","bbbbbbb","ccc","dddddddd"))
}
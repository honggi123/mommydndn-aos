package com.mommydndn.app.ui.components.box

import android.widget.EditText
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mommydndn.app.ui.theme.Grey400
import com.mommydndn.app.ui.theme.Grey50
import com.mommydndn.app.ui.theme.Grey500
import com.mommydndn.app.ui.theme.Grey800
import com.mommydndn.app.ui.theme.Salmon600
import com.mommydndn.app.ui.theme.White
import com.mommydndn.app.ui.theme.caption100
import com.mommydndn.app.ui.theme.paragraph300
import com.mommydndn.app.ui.theme.paragraph400

@Composable
fun PostTextFieldBox(
    modifier: Modifier = Modifier,
    title: String = "",
    onTitleTextChanged: (String) -> Unit = {},
    content: String = "",
    onContentTextChanged: (String) -> Unit = {}
) {
    var titleCharCount by remember { mutableStateOf(0) }
    var contentCharCount by remember { mutableStateOf(0) }

    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.Top),
        modifier = modifier
            .background(color = Color.White)
    ) {
        BasicTextField(
            value = title,
            decorationBox = {
                if (title.isEmpty()) {
                    Text(
                        text = "(필수) 글 제목을 입력하세요",
                        style = MaterialTheme.typography.paragraph400.copy(
                            fontWeight = FontWeight.Bold,
                            color = Grey400
                        )
                    )
                }
                it.invoke()
            },
            onValueChange = {
                if (titleCharCount <= 30) {
                    onTitleTextChanged(it)
                    titleCharCount += 1
                }
            },
            textStyle = MaterialTheme.typography.paragraph400.copy(
                fontWeight = FontWeight.Bold,
                color = Grey800,
            ),
            cursorBrush = SolidColor(Salmon600),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, bottom = 16.dp, start = 6.dp, end = 6.dp),
        )
        Divider(
            color = Grey50,
            modifier = Modifier
                .fillMaxWidth()
        )
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.End,
            modifier = Modifier
                .fillMaxWidth()
                .height(199.dp)
                .clip(shape = RoundedCornerShape(6.dp))
                .padding(
                    horizontal = 6.dp,
                    vertical = 8.dp
                )
        ) {
            BasicTextField(
                value = content,
                decorationBox = {
                    if (content.isEmpty()) {
                        Text(
                            text = "(선택) 상대방을 불쾌하게 하는 공고를 올리지 말아주세요. 일정 횟수 이상 차단을 당하면 서비스 이용이 제한될 수 있어요.",
                            style = MaterialTheme.typography.paragraph300.copy(
                                fontWeight = FontWeight.Normal,
                                color = Grey400
                            )
                        )
                    }
                    it.invoke()
                },
                cursorBrush = SolidColor(Salmon600),
                onValueChange = {
                    if (contentCharCount <= 500) {
                        onContentTextChanged(it)
                        contentCharCount += 1
                    }
                },
                textStyle = MaterialTheme.typography.paragraph300.copy(
                    fontWeight = FontWeight.Normal,
                    color = Grey800
                ),
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Text
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(top = 16.dp, bottom = 16.dp, start = 6.dp, end = 6.dp),
            )
            Text(
                text = "$contentCharCount/500자",
                style = MaterialTheme.typography.caption100.copy(
                    fontWeight = FontWeight.Normal,
                    color = Grey500
                )
            )
        }
    }
}

@Preview
@Composable
fun previewPostTextFieldBox() {
    PostTextFieldBox()
}

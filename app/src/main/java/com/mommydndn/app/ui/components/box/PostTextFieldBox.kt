package com.mommydndn.app.ui.components.box

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mommydndn.app.R
import com.mommydndn.app.ui.theme.Grey400
import com.mommydndn.app.ui.theme.Grey50
import com.mommydndn.app.ui.theme.Grey500
import com.mommydndn.app.ui.theme.Grey800
import com.mommydndn.app.ui.theme.Salmon600
import com.mommydndn.app.ui.theme.caption100
import com.mommydndn.app.ui.theme.paragraph300
import com.mommydndn.app.ui.theme.paragraph400

@Composable
fun PostTextFieldBox(
    title: String,
    onTitleChange: (String) -> Unit,
    content: String,
    onContentChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val titleLength = remember {
        mutableIntStateOf(title.length)
    }

    val contentLength = remember {
        mutableIntStateOf(content.length)
    }

    Column(
        modifier = modifier.background(color = Color.White),
        verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.Top),
    ) {
        BasicTextField(
            value = title,
            onValueChange = { title ->
                if (title.length <= 30) {
                    onTitleChange(title)

                    titleLength.intValue = title.length
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 6.dp, vertical = 16.dp),
            textStyle = MaterialTheme.typography.paragraph400.merge(
                color = Grey800,
                fontWeight = FontWeight.Bold,
            ),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text
            ),
            singleLine = true,
            cursorBrush = SolidColor(Salmon600),
            decorationBox = {
                if (title.isEmpty()) {
                    Text(
                        text = stringResource(R.string.post_title_hint_text),
                        style = MaterialTheme.typography.paragraph400.merge(
                            color = Grey400,
                            fontWeight = FontWeight.Bold,
                        )
                    )
                }
            },
        )

        Divider(
            modifier = Modifier.fillMaxWidth(),
            color = Grey50,
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .heightIn(min = 199.dp) // todo
                .padding(horizontal = 6.dp, vertical = 8.dp),
            horizontalAlignment = Alignment.End,
        ) {
            BasicTextField(
                value = content,
                onValueChange = { content ->
                    if (content.length <= 500) {
                        contentLength.intValue = content.length

                        onContentChange(content)
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1F)
                    .padding(horizontal = 8.dp, vertical = 16.dp),
                textStyle = MaterialTheme.typography.paragraph300.merge(
                    color = Grey800,
                    fontWeight = FontWeight.Normal,
                ),
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Text
                ),
                cursorBrush = SolidColor(Salmon600),
                decorationBox = {
                    if (content.isEmpty()) {
                        Text(
                            text = stringResource(R.string.post_content_hint_text),
                            style = MaterialTheme.typography.paragraph300.copy(
                                fontWeight = FontWeight.Normal,
                                color = Grey400
                            )
                        )
                    }
                },
            )

            Text(
                text = stringResource(R.string.post_content_length, contentLength.intValue),
                style = MaterialTheme.typography.caption100.merge(
                    color = Grey500,
                    fontWeight = FontWeight.Normal,
                )
            )
        }
    }
}

@Preview
@Composable
fun previewPostTextFieldBox() {
    PostTextFieldBox(
        title = "",
        onTitleChange = {},
        content = "",
        onContentChange = {},
    )
}

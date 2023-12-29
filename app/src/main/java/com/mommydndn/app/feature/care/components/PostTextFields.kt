package com.mommydndn.app.feature.care.components

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mommydndn.app.R
import com.mommydndn.app.ui.theme.Grey400
import com.mommydndn.app.ui.theme.Grey500
import com.mommydndn.app.ui.theme.Grey800
import com.mommydndn.app.ui.theme.Salmon600
import com.mommydndn.app.ui.theme.White
import com.mommydndn.app.ui.theme.caption100
import com.mommydndn.app.ui.theme.paragraph300
import com.mommydndn.app.ui.theme.paragraph400

@Composable
fun TitleTextField(
    title: String,
    onTitleChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    singleLine: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
) {
    BasicTextField(
        value = title,
        onValueChange = {
            if (it.length <= 30) {
                onTitleChange(it)
            }
        },
        modifier = modifier,
        textStyle = MaterialTheme.typography.paragraph400.merge(
            color = Grey800,
            fontWeight = FontWeight.Bold,
        ),
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Text
        ),
        singleLine = singleLine,
        visualTransformation = VisualTransformation.None,
        interactionSource = interactionSource,
        cursorBrush = SolidColor(Salmon600),
    ) {
        TextFieldDefaults.TextFieldDecorationBox(
            value = title,
            innerTextField = it,
            enabled = enabled,
            singleLine = singleLine,
            visualTransformation = VisualTransformation.None,
            interactionSource = interactionSource,
            placeholder = {
                Text(
                    text = stringResource(id = R.string.post_title_hint_text),
                    style = MaterialTheme.typography.paragraph400.merge(
                        color = Grey400,
                        fontWeight = FontWeight.Bold,
                    ),
                )
            },
            contentPadding = PaddingValues(horizontal = 6.dp, vertical = 16.dp)
        )
    }
}

@Composable
fun ContentTextField(
    content: String,
    onContentChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    singleLine: Boolean = false,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
) {
    var length by remember { mutableIntStateOf(content.length) }

    Column(
        // todo: Box?
        modifier = modifier.wrapContentHeight(),
        horizontalAlignment = Alignment.End,
    ) {
        BasicTextField(
            value = content,
            onValueChange = { content ->
                if (content.length <= 500) {
                    length = content.length

                    onContentChange(content)
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .heightIn(min = 199.dp),
            enabled = enabled,
            textStyle = MaterialTheme.typography.paragraph300.merge(
                color = Grey800,
                fontWeight = FontWeight.Normal,
            ),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text
            ),
            singleLine = singleLine,
            cursorBrush = SolidColor(Salmon600),
            decorationBox = {
                TextFieldDefaults.TextFieldDecorationBox(
                    value = content,
                    innerTextField = it,
                    enabled = enabled,
                    singleLine = singleLine,
                    visualTransformation = VisualTransformation.None,
                    interactionSource = interactionSource,
                    placeholder = {
                        Text(
                            text = stringResource(R.string.post_content_hint_text),
                            style = MaterialTheme.typography.paragraph300.merge(
                                color = Grey400,
                                fontWeight = FontWeight.Normal,
                            ),
                        )
                    },
                    contentPadding = PaddingValues(horizontal = 6.dp, vertical = 8.dp)
                )
            }
        )

        Text(
            text = stringResource(R.string.post_content_length, length),
            modifier = Modifier.padding(horizontal = 6.dp, vertical = 8.dp),
            style = MaterialTheme.typography.caption100.merge(
                color = Grey500,
                fontWeight = FontWeight.Normal,
            )
        )
    }
}

@Preview
@Composable
private fun TitleTextField_EmptyText() {
    TitleTextField(
        title = "",
        onTitleChange = {},
        modifier = Modifier
            .fillMaxWidth()
            .background(White),
    )
}

@Preview
@Composable
private fun TitleTextField_WithText() {
    TitleTextField(
        title = "글 제목",
        onTitleChange = {},
        modifier = Modifier
            .fillMaxWidth()
            .background(White),
    )
}

@Preview
@Composable
private fun ContentTextField_EmptyText() {
    ContentTextField(
        content = "",
        onContentChange = {},
        modifier = Modifier.background(White),
    )
}

@Preview
@Composable
private fun ContentTextField_WithText() {
    ContentTextField(
        content = stringResource(R.string.lorem_ipsum),
        onContentChange = {},
        modifier = Modifier.background(White),
    )
}
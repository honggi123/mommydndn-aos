package com.mommydndn.app.deprecated.components.inputfield

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.mommydndn.app.ui.theme.Grey400
import com.mommydndn.app.ui.theme.Grey50
import com.mommydndn.app.ui.theme.Grey500
import com.mommydndn.app.ui.theme.Salmon500
import com.mommydndn.app.ui.theme.Salmon600
import com.mommydndn.app.ui.theme.caption200
import com.mommydndn.app.ui.theme.paragraph300

@Composable
fun TextInpuField(
    modifier: Modifier = Modifier,
    label: String = "",
    value: String = "",
    descriptionText: String = "",
    placeHolderText: String = "",
    rightText: String? = "",
    isError: Boolean = false,
    onValueChanged: (String) -> Unit = {},
    focusRequester: FocusRequester,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None
) {

    var isFocused by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        if (isFocused) focusRequester.requestFocus()
    }

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(6.dp, Alignment.Top)
    ) {
        if (label.isNotBlank()) {
            Text(
                text = label,
                style = MaterialTheme.typography.paragraph300.copy(
                    fontWeight = FontWeight.Bold,
                    color = Grey500
                )
            )
        }

        OutlinedTextField(
            modifier = Modifier
                .width(342.dp)
                .height(56.dp)
                .focusRequester(focusRequester)
                .onFocusChanged {
                    isFocused = it.isFocused
                },
            shape = RoundedCornerShape(12.dp),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Grey50,
                focusedIndicatorColor = Salmon500,
                disabledIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                errorCursorColor = Salmon600,
                errorIndicatorColor = Salmon600,
                cursorColor = Salmon600,
            ),
            value = value,
            onValueChange = {
                onValueChanged(it)
            },
            textStyle = MaterialTheme.typography.paragraph300.copy(
                fontWeight = FontWeight.Normal,
                color = Grey500
            ),
            trailingIcon = {
                if (!rightText.isNullOrBlank()) {
                    Text(
                        text = rightText,
                        style = MaterialTheme.typography.paragraph300.copy(
                            fontWeight = FontWeight.Normal,
                            color = Grey400
                        ),
                        modifier = Modifier
                            .wrapContentHeight(align = Alignment.CenterVertically)
                    )
                }
            },
            placeholder = {
                Text(
                    text = placeHolderText,
                    style = MaterialTheme.typography.paragraph300.copy(
                        fontWeight = FontWeight.Normal,
                        color = Grey400
                    )
                )
            },
            keyboardOptions = keyboardOptions,
            isError = isError,
            visualTransformation = visualTransformation,
            singleLine = true
        )
        if (descriptionText.isNotBlank()) {
            Text(
                text = descriptionText,
                style = MaterialTheme.typography.caption200.copy(
                    fontWeight = FontWeight.Normal,
                    color = if (isError) Salmon600 else Grey400
                )
            )
        }
    }

}


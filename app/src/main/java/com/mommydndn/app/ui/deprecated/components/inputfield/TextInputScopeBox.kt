package com.mommydndn.app.ui.deprecated.components.inputfield

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import com.mommydndn.app.ui.theme.Grey800
import com.mommydndn.app.ui.theme.Salmon500
import com.mommydndn.app.ui.theme.Salmon600
import com.mommydndn.app.ui.theme.caption200
import com.mommydndn.app.ui.theme.heading700
import com.mommydndn.app.ui.theme.paragraph300

@Composable
fun TextInputScopeBox(
    modifier: Modifier = Modifier,
    label: String = "",
    value1: String,
    value2: String,
    onValue1Changed: (String) -> Unit,
    onValue2Changed: (String) -> Unit,
    isSelected: Boolean = false,
    descriptionText: String = "",
    isErrorOption1: Boolean = false,
    isErrorOption2: Boolean = false,
    placeHolder1Text: String = "",
    placeHolder2Text: String = "",
    option1FocusRequester: FocusRequester,
    option2FocusRequester: FocusRequester,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    isError: Boolean = false
) {
    val textColor = if (isSelected) Grey800 else Grey400

    var iOption1Focused by remember { mutableStateOf(false) }
    var isOption2Focused by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        if (iOption1Focused) option1FocusRequester.requestFocus()
    }

    LaunchedEffect(Unit) {
        if (isOption2Focused) option2FocusRequester.requestFocus()
    }

    Column(
        verticalArrangement = Arrangement.spacedBy(6.dp, Alignment.Top),
        modifier = modifier.width(316.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.Start),
            modifier = Modifier
                .width(width = 342.dp)
        ) {
            Text(
                text = label,
                style = MaterialTheme.typography.paragraph300.copy(
                    fontWeight = FontWeight.Bold,
                    color = Grey500
                )
            )
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(2.dp, Alignment.CenterHorizontally),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
        ) {

            OutlinedTextField(
                modifier = Modifier
                    .weight(1f)
                    .height(56.dp)
                    .focusRequester(option1FocusRequester)
                    .onFocusChanged {
                        iOption1Focused = it.isFocused
                    },
                shape = RoundedCornerShape(12.dp),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Grey50,
                    focusedIndicatorColor = Salmon500,
                    disabledIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    errorIndicatorColor = Salmon600,
                    cursorColor = Salmon600
                ),
                value = value1,
                onValueChange = {
                    onValue1Changed(it)
                },
                textStyle = MaterialTheme.typography.paragraph300.copy(
                    fontWeight = FontWeight.Normal,
                    color = Grey500
                ),
                trailingIcon = {
                    Text(
                        text = "만원",
                        style = MaterialTheme.typography.paragraph300.copy(
                            fontWeight = FontWeight.Normal,
                            color = Grey400
                        ),
                        modifier = Modifier
                            .wrapContentHeight(align = Alignment.CenterVertically)
                    )
                },
                placeholder = {
                    Text(
                        text = placeHolder1Text,
                        style = MaterialTheme.typography.paragraph300.copy(
                            fontWeight = FontWeight.Normal,
                            color = Grey400
                        )
                    )
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number
                ),
                visualTransformation = visualTransformation,
                isError = isErrorOption1
            )

            Text(
                text = "~",
                style = MaterialTheme.typography.heading700.copy(
                    fontWeight = FontWeight.Normal,
                    color = textColor
                )
            )

            OutlinedTextField(
                modifier = Modifier
                    .weight(1f)
                    .height(56.dp)
                    .focusRequester(option2FocusRequester)
                    .onFocusChanged {
                        isOption2Focused = it.isFocused
                    },
                shape = RoundedCornerShape(12.dp),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Grey50,
                    focusedIndicatorColor = Salmon500,
                    disabledIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    errorIndicatorColor = Salmon600,
                    cursorColor = Salmon600
                ),
                value = value2,
                onValueChange = {
                    onValue2Changed(it)
                },
                visualTransformation = visualTransformation,
                textStyle = MaterialTheme.typography.paragraph300.copy(
                    fontWeight = FontWeight.Normal,
                    color = Grey500
                ),
                trailingIcon = {
                    Text(
                        text = "만원",
                        style = MaterialTheme.typography.paragraph300.copy(
                            fontWeight = FontWeight.Normal,
                            color = Grey400
                        ),
                        modifier = Modifier
                            .wrapContentHeight(align = Alignment.CenterVertically)
                    )
                },
                placeholder = {
                    Text(
                        text = placeHolder2Text,
                        style = MaterialTheme.typography.paragraph300.copy(
                            fontWeight = FontWeight.Normal,
                            color = Grey400
                        )
                    )
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number
                ),
                isError = isErrorOption2
            )
        }
        Text(
            text = descriptionText,
            style = MaterialTheme.typography.caption200.copy(
                fontWeight = FontWeight.Normal,
                color = if (isError) Salmon600 else Grey400
            )
        )
    }
}

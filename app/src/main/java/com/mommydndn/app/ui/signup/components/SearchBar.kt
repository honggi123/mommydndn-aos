package com.mommydndn.app.ui.signup.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.mommydndn.app.R
import com.mommydndn.app.ui.care.post.components.TopAppBarHeight
import com.mommydndn.app.ui.theme.Grey400
import com.mommydndn.app.ui.theme.Grey700
import com.mommydndn.app.ui.theme.Salmon600
import com.mommydndn.app.ui.theme.paragraph300

@Composable
internal fun SearchBar(
    query: String,
    onValueChange: (String) -> Unit,
    onBackClick: () -> Unit,
    onSearchClick: () -> Unit,
    onClearQueryClick: () -> Unit,
    modifier: Modifier = Modifier,
    placeholderText: String = stringResource(R.string.search_bar_hint),
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(TopAppBarHeight)
            .padding(horizontal = 20.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            painter = painterResource(id = R.drawable.icon_arrow_left),
            contentDescription = "SearchBar_ArrowLeft",
            modifier = Modifier
                .size(36.dp)
                .clickable(onClick = onBackClick),
            tint = Grey400,
        )

        TextField(
            value = query,
            onValueChange = onValueChange,
            modifier = Modifier.weight(1F),
            textStyle = MaterialTheme.typography.paragraph300.merge(
                color = Grey700,
                fontWeight = FontWeight.Medium,
            ),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search,
                keyboardType = KeyboardType.Text,
                capitalization = KeyboardCapitalization.Words,
                autoCorrect = true
            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    onSearchClick()
                }
            ),
            singleLine = true,
            placeholder = {
                Text(
                    text = placeholderText, // TODO: ex. 뒤로 짤림
                    modifier = Modifier.weight(1F),
                    color = Grey400,
                    maxLines = 1,
                    style = MaterialTheme.typography.paragraph300.merge(
                        fontWeight = FontWeight.Medium,
                    ),
                )
            },
            trailingIcon = {
                if (query.isNotEmpty()) {
                    Row {
                        Spacer(modifier = Modifier.width(6.dp))

                        Icon(
                            painter = painterResource(id = R.drawable.icon_circle_x),
                            contentDescription = "SearchBar_CircleX",
                            modifier = Modifier
                                .size(36.dp)
                                .padding(8.dp)
                                .clickable(
                                    interactionSource = remember { MutableInteractionSource() },
                                    indication = null,
                                    onClick = onClearQueryClick,
                                ),
                            tint = Color.Unspecified,
                        )
                    }
                }
            },
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.White,
                cursorColor = Salmon600,
                focusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
            ),
        )
    }
}
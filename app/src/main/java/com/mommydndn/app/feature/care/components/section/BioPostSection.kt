package com.mommydndn.app.feature.care.components.section

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
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
import com.mommydndn.app.ui.theme.Grey50
import com.mommydndn.app.ui.theme.Grey800
import com.mommydndn.app.ui.theme.Salmon600
import com.mommydndn.app.ui.theme.paragraph300

@Composable
internal fun BioPostSection(
    bio: String,
    onBioChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    title: String = stringResource(R.string.bio),
    subtitle: String = stringResource(id = R.string.required),
    enabled: Boolean = true,
    singleLine: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
) {
    PostSection(
        title = title,
        subtitle = subtitle,
        modifier = modifier,
    ) {
        BasicTextField(
            value = bio,
            onValueChange = {
                if (it.length <= 200) {
                    onBioChange(it)
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Grey50, shape = RoundedCornerShape(12.dp)),
            textStyle = MaterialTheme.typography.paragraph300.merge(
                color = Grey800,
                fontWeight = FontWeight.Normal,
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
                value = bio,
                innerTextField = it,
                enabled = enabled,
                singleLine = singleLine,
                visualTransformation = VisualTransformation.None,
                interactionSource = interactionSource,
                placeholder = {
                    Text(
                        text = stringResource(R.string.care_provider_bio),
                        style = MaterialTheme.typography.paragraph300.merge(
                            color = Grey400,
                            fontWeight = FontWeight.Normal,
                        ),
                    )
                },
                contentPadding = PaddingValues(16.dp)
            )
        }
    }
}

@Preview
@Composable
private fun EmptyBioPostField() {
    BioPostSection(
        bio = "",
        onBioChange = {},
        modifier = Modifier,
    )
}

@Preview
@Composable
private fun NotEmptyBioPostField() {
    BioPostSection(
        bio = "타인을 돕고 아이들을 돌볼때 가장 보람을 느낍니다 감사합니다",
        onBioChange = {},
        modifier = Modifier,
    )
}
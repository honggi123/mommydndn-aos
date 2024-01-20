package com.mommydndn.app.ui.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mommydndn.app.R
import com.mommydndn.app.ui.theme.Grey100
import com.mommydndn.app.ui.theme.Grey400
import com.mommydndn.app.ui.theme.Grey600
import com.mommydndn.app.ui.theme.Typography
import com.mommydndn.app.ui.theme.White
import com.mommydndn.app.ui.theme.paragraph300
import com.mommydndn.app.ui.theme.paragraph400

@Composable
internal fun ReviewSection(
    onReviewClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    buildAnnotatedString {
        withStyle(SpanStyle(color = Grey400)) {
            append("지금까지의 마미든든에 대한")
        }
    }
    // TODO: STRING_RESOURCE
    val text = buildAnnotatedString {
        withStyle(SpanStyle(color = Grey400)) {
            append("지금까지의 마미든든에 대한")
        }

        append("\n")

        withStyle(SpanStyle(color = Grey600)) {
            append("소중한 의견을 남겨주세요")
        }
    }

    Box(modifier = modifier) {
        Row(modifier = Modifier.padding(horizontal = 32.dp, vertical = 56.dp)) {
            Icon(
                painter = painterResource(id = R.drawable.icon_heart_letter),
                contentDescription = "ReviewSection_HeartLetter",
                tint = Color.Unspecified,
            )

            Spacer(modifier = Modifier.width(24.dp))

            Column {
                Text(
                    text = text,
                    modifier = Modifier,
                    style = Typography.paragraph400.merge(
                        fontWeight = FontWeight.Bold
                    )
                )

                Spacer(modifier = Modifier.height(14.dp))

                Text(
                    text = stringResource(R.string.review),
                    modifier = Modifier
                        .background(Grey100, RoundedCornerShape(12.dp))
                        .clip(RoundedCornerShape(12.dp))
                        .padding(horizontal = 16.dp, vertical = 12.dp)
                        .clickable(onClick = onReviewClick),
                    color = Grey600,
                    style = Typography.paragraph300.merge(
                        fontWeight = FontWeight.Medium
                    )
                )
            }
        }
    }
}

@Preview
@Composable
private fun ReviewSectionPreview() {
    ReviewSection(
        onReviewClick = {},
        modifier = Modifier
            .background(White)
            .fillMaxWidth(),
    )
}
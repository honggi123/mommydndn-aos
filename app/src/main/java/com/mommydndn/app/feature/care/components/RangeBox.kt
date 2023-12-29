package com.mommydndn.app.feature.care.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mommydndn.app.R
import com.mommydndn.app.ui.theme.Grey400
import com.mommydndn.app.ui.theme.Grey50
import com.mommydndn.app.ui.theme.Grey500
import com.mommydndn.app.ui.theme.Grey800
import com.mommydndn.app.ui.theme.Salmon600
import com.mommydndn.app.ui.theme.White
import com.mommydndn.app.ui.theme.heading700
import com.mommydndn.app.ui.theme.paragraph300

@Composable
internal fun RangeBox(
    label: String,
    option1Hint: String,
    option2Hint: String,
    onOption1Click: () -> Unit,
    onOption2Click: () -> Unit,
    modifier: Modifier = Modifier,
    option1Text: String = "",
    option2Text: String = "",
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(6.dp),
        horizontalAlignment = Alignment.Start,
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.paragraph300.merge(
                color = Grey500,
                fontWeight = FontWeight.Bold,
            )
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Row(
                modifier = Modifier
                    .height(height = 56.dp)
                    .clip(shape = RoundedCornerShape(12.dp))
                    .run {
                        if (option1Text.isNotBlank()) {
                            border(1.dp, Salmon600, RoundedCornerShape(12.dp))
                        } else {
                            this
                        }
                    }
                    .background(color = Grey50)
                    .weight(1F)
                    .padding(start = 20.dp, top = 12.dp, end = 8.dp, bottom = 12.dp)
                    .clickable(onClick = onOption1Click),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = option1Text.ifBlank { option1Hint },
                    style = MaterialTheme.typography.paragraph300.merge(
                        color = if (option1Text.isNotBlank()) Grey800 else Grey400,
                        fontWeight = FontWeight.Normal,
                    )
                )

                Image(
                    painter = painterResource(id = R.drawable.icon_arrow_down),
                    contentDescription = "RangeBox_ArrowDown",
                    modifier = Modifier.size(size = 28.dp),
                )
            }

            Text(
                text = stringResource(id = R.string.tilde),
                style = MaterialTheme.typography.heading700.merge(
                    color = Grey400,
                    fontWeight = FontWeight.Normal,
                )
            )

            Row(
                modifier = Modifier
                    .height(height = 56.dp)
                    .clip(shape = RoundedCornerShape(12.dp))
                    .run {
                        if (option2Text.isNotBlank()) {
                            border(1.dp, Salmon600, RoundedCornerShape(12.dp))
                        } else {
                            this
                        }
                    }
                    .weight(1F)
                    .background(color = Grey50)
                    .padding(start = 20.dp, top = 12.dp, end = 8.dp, bottom = 12.dp)
                    .clickable(onClick = onOption2Click),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = option2Text.ifBlank { option2Hint },
                    style = MaterialTheme.typography.paragraph300.merge(
                        color = if (option2Text.isNotBlank()) Grey800 else Grey400,
                        fontWeight = FontWeight.Normal,
                    )
                )

                Image(
                    painter = painterResource(id = R.drawable.icon_arrow_down),
                    contentDescription = "RangeBox_ArrowDown",
                    modifier = Modifier.size(size = 28.dp),
                )
            }
        }
    }
}

@Preview
@Composable
private fun PreviewRangeBox_NoValues() {
    RangeBox(
        label = "시간",
        option1Hint = "시작시간",
        option2Hint = "종료시간",
        onOption1Click = {},
        onOption2Click = {},
        modifier = Modifier
            .background(White)
            .padding(horizontal = 16.dp, vertical = 12.dp),
    )
}

@Preview
@Composable
private fun PreviewRangeBox_WithValues() {
    RangeBox(
        label = "시간",
        option1Hint = "시작시간",
        option2Hint = "종료시간",
        onOption1Click = {},
        onOption2Click = {},
        modifier = Modifier
            .background(White)
            .padding(horizontal = 16.dp, vertical = 12.dp),
        option1Text = "17:00",
        option2Text = "22:00",
    )
}

@Preview
@Composable
private fun PreviewRangeBox_WithValue() {
    RangeBox(
        label = "시간",
        option1Hint = "시작시간",
        option2Hint = "종료시간",
        onOption1Click = {},
        onOption2Click = {},
        modifier = Modifier
            .background(White)
            .padding(horizontal = 16.dp, vertical = 12.dp),
        option1Text = "17:00",
    )
}
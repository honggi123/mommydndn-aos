package com.mommydndn.app.deprecated.components.box

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mommydndn.app.R
import com.mommydndn.app.deprecated.components.list.CheckMarkListItem
import com.mommydndn.app.ui.theme.Grey400
import com.mommydndn.app.ui.theme.Grey50
import com.mommydndn.app.ui.theme.Grey500
import com.mommydndn.app.ui.theme.Grey800
import com.mommydndn.app.ui.theme.Salmon600
import com.mommydndn.app.ui.theme.heading700
import com.mommydndn.app.ui.theme.paragraph300

@Composable
fun SelectScopeBox(
    modifier: Modifier = Modifier,
    label: String = "",
    option1Text: String,
    option2Text: String,
    onOption1Clicked: () -> Unit,
    onOption2Clicked: () -> Unit,
    isChecked: Boolean = false,
    onCheckedChange: (Boolean) -> Unit = {},
    isOption1Selected: Boolean = false,
    isOption2Selected: Boolean = false,
    checkListText: String = ""
) {
    val option1TextColor = if (isOption1Selected) Grey800 else Grey400
    val option2TextColor = if (isOption2Selected) Grey800 else Grey400

    Column(
        verticalArrangement = Arrangement.spacedBy(6.dp, Alignment.Top),
        modifier = modifier
    ) {
        if (label.isNotBlank()) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.Start),
                modifier = Modifier
                    .width(width = 342.dp)
                    .padding(horizontal = 6.dp)
            ) {
                Text(
                    text = label,
                    style = MaterialTheme.typography.paragraph300.copy(
                        fontWeight = FontWeight.Bold,
                        color = Grey500
                    )
                )
            }
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(2.dp, Alignment.CenterHorizontally),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .height(height = 56.dp)
                    .clip(shape = RoundedCornerShape(12.dp))
                    .border(
                        1.dp,
                        if (isOption1Selected) Salmon600 else Color.Transparent,
                        RoundedCornerShape(12.dp)
                    )
                    .background(color = Grey50)
                    .weight(1f)
                    .padding(
                        start = 20.dp,
                        end = 8.dp,
                        top = 12.dp,
                        bottom = 12.dp
                    )
                    .clickable { onOption1Clicked() }

            ) {
                Text(
                    text = option1Text,
                    style = MaterialTheme.typography.paragraph300.copy(
                        fontWeight = FontWeight.Normal,
                        color = option1TextColor
                    )
                )
                Image(
                    painter = painterResource(id = R.drawable.icon_arrow_down),
                    contentDescription = "Icon/arrow-down-line",
                    modifier = Modifier
                        .size(size = 28.dp)
                )
            }

            Text(
                text = "~",
                style = MaterialTheme.typography.heading700.copy(
                    fontWeight = FontWeight.Normal,
                    color = Grey400
                )
            )

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .width(width = 342.dp)
                    .height(height = 56.dp)
                    .clip(shape = RoundedCornerShape(12.dp))
                    .border(
                        1.dp,
                        if (isOption2Selected) Salmon600 else Color.Transparent,
                        RoundedCornerShape(12.dp)
                    )
                    .weight(1f)
                    .background(color = Grey50)
                    .padding(
                        start = 20.dp,
                        end = 8.dp,
                        top = 12.dp,
                        bottom = 12.dp
                    )
                    .clickable { onOption2Clicked() }
            ) {
                Text(
                    text = option2Text,
                    style = MaterialTheme.typography.paragraph300.copy(
                        fontWeight = FontWeight.Normal,
                        color = option2TextColor
                    )
                )
                Image(
                    painter = painterResource(id = R.drawable.icon_arrow_down),
                    contentDescription = "",
                    modifier = Modifier
                        .size(size = 28.dp)
                )
            }
        }
        if (!checkListText.isEmpty()) {
            CheckMarkListItem(
                checked = isChecked,
                onCheckedChange = onCheckedChange,
                text = checkListText
            )
        }
    }
}

@Composable
@Preview
fun SelectScopeBoxPreview() {
    SelectScopeBox(
        label = "label",
        option1Text = "Option 1",
        option2Text = "Option 2",
        onOption1Clicked = {},
        onOption2Clicked = {},
        isChecked = true,
        onCheckedChange = {},
        checkListText = "Check this"
    )
}
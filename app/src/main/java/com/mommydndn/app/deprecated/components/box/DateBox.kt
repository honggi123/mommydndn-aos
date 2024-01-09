package com.mommydndn.app.deprecated.components.box

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.mommydndn.app.R
import com.mommydndn.app.ui.theme.Grey400
import com.mommydndn.app.ui.theme.Grey50
import com.mommydndn.app.ui.theme.Grey800
import com.mommydndn.app.ui.theme.paragraph300

@Composable
fun DateBox(
    modifier: Modifier = Modifier,
    text: String = "",
    isSelected: Boolean = false,
    onClick: () -> Unit
) {

    val optionTextColor = if (isSelected) Grey800 else Grey400

    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .height(height = 56.dp)
            .clip(shape = RoundedCornerShape(12.dp))
            .background(color = Grey50)
            .padding(
                start = 20.dp,
                end = 8.dp,
                top = 12.dp,
                bottom = 12.dp
            )
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.paragraph300.copy(
                fontWeight = FontWeight.Normal,
                color = optionTextColor
            )
        )
        Image(
            painter = painterResource(id = R.drawable.icon_circle_x_light_grey),
            contentDescription = "Icon/ic_x_circle",
            modifier = Modifier
                .size(size = 21.dp)
                .clickable { onClick() },
        )
    }
}
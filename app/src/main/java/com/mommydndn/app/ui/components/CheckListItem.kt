package com.mommydndn.app.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.mommydndn.app.R
import com.mommydndn.app.ui.theme.Grey200
import com.mommydndn.app.ui.theme.Grey600
import com.mommydndn.app.ui.theme.Salmon600
import com.mommydndn.app.ui.theme.paragraph300

@Composable
fun CheckListItem(
    checked: Boolean,
    text: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    iconPainter: Painter = painterResource(
        id = if (checked) {
            R.drawable.icon_checked_mark
        } else {
            R.drawable.icon_unchecked_mark
        }
    ),
    checkedTint: Color = Salmon600,
    uncheckedTint: Color = Grey200,
    textColor: Color = Grey600,
    textStyle: TextStyle = MaterialTheme.typography.paragraph300.merge(
        fontWeight = FontWeight.Normal,
    ),
) {
    Row(
        modifier = modifier.clickable(
            interactionSource = remember { MutableInteractionSource() },
            indication = null,
            onClick = onClick
        ),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            modifier = Modifier.size(32.dp),
            painter = iconPainter,
            contentDescription = "CheckableListItem_CheckMark",
            tint = if (checked) {
                checkedTint
            } else {
                uncheckedTint
            },
        )

        Text(
            modifier = Modifier.fillMaxWidth(),
            text = text,
            color = textColor,
            style = textStyle,
        )
    }
}
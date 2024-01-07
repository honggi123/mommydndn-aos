package com.mommydndn.app.ui.components.check

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.mommydndn.app.R
import com.mommydndn.app.ui.theme.Grey600
import com.mommydndn.app.ui.theme.paragraph300

@Composable
internal fun CheckboxListItem(
    checked: Boolean,
    onClick: () -> Unit,
    name: String,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .clickable(onClick = onClick)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                // todo: 보이는 사이즈가 조금 다르다
                painter = painterResource(
                    id = if (checked) {
                        R.drawable.icon_checked_checkbox
                    } else {
                        R.drawable.icon_unchecked_checkbox
                    }
                ),
                contentDescription = "CheckboxListItem_Checkbox",
                modifier = Modifier.size(32.dp).run {
                    if (checked) {
                        this
                    } else {
                        padding(1.5.dp)
                    }
                },
                tint = Color.Unspecified,
            )

            Text(
                text = name,
                modifier = Modifier.weight(1F),
                style = MaterialTheme.typography.paragraph300.copy(
                    color = Grey600,
                    fontWeight = FontWeight.Medium,
                ),
            )
        }
    }
}
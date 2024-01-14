package com.mommydndn.app.deprecated.components.list

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mommydndn.app.R
import com.mommydndn.app.ui.theme.Grey600
import com.mommydndn.app.ui.theme.MommydndnTheme
import com.mommydndn.app.ui.theme.paragraph300

@Composable
fun CheckBoxListItem(
    checked: Boolean,
    onCheckedChange: ((Boolean) -> Unit),
    text: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .width(318.dp)
            .wrapContentHeight()
            .clickable {
                onCheckedChange(!checked)
            }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = Modifier.size(32.dp),
                painter = painterResource(
                    id = if (checked) R.drawable.icon_checked_checkbox else R.drawable.icon_unchecked_checkbox
                ),
                contentScale = ContentScale.Crop,
                contentDescription = ""
            )

            Spacer(modifier = Modifier.size(8.dp))
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = text, style = MaterialTheme.typography.paragraph300.copy(
                    fontWeight = FontWeight.Medium,
                    color = Grey600
                )
            )
        }
    }

}

@Preview
@Composable
fun previewCheckListItem() {
    MommydndnTheme {
        var state by remember { mutableStateOf(false) }
        CheckBoxListItem(state, { state = it }, "리스트 아이템")
    }
}

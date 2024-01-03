package com.mommydndn.app.ui.deprecated.components.box

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.mommydndn.app.ui.deprecated.components.button.SelectButton
import com.mommydndn.app.data.model.common.SelectButtonContent
import com.mommydndn.app.ui.theme.Grey500
import com.mommydndn.app.ui.theme.paragraph300

@Composable
fun SelectButtonScopeBox(
    modifier: Modifier = Modifier,
    label: String = "",
    list: List<SelectButtonContent>
) {
    Column(
        modifier
            .padding(horizontal = 6.dp)
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.paragraph300.copy(
                fontWeight = FontWeight.Bold,
                color = Grey500
            )
        )
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 4.dp, vertical = 12.dp)
        ) {
            list.forEach { content ->
                SelectButton(
                    modifier = Modifier.size(36.dp),
                    content = content
                )
            }
        }
    }
}



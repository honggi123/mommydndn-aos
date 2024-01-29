package com.mommydndn.app.ui.components.modal

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.mommydndn.app.R
import com.mommydndn.app.ui.components.check.CheckListItem
import com.mommydndn.app.ui.theme.Grey100
import com.mommydndn.app.ui.theme.Grey50
import com.mommydndn.app.ui.theme.Grey600
import com.mommydndn.app.ui.theme.Salmon600
import com.mommydndn.app.ui.theme.White
import com.mommydndn.app.ui.theme.paragraph300

@Composable
internal fun CheckListModal(
    values: Map<String, Boolean>,
    onCloseClick: () -> Unit,
    onActionClick: () -> Unit,
    onToggleAllClick: () -> Unit,
    onValueClick: (String) -> Unit,
    modifier: Modifier = Modifier,
    allText: String = stringResource(R.string.agree_all),
    dismissText: String = stringResource(id = R.string.close),
    actionText: String = stringResource(id = R.string.move_on),
    enabled: Boolean = false,
) {
    MommydndnModal(
        dismissText = dismissText,
        onDismissClick = onCloseClick,
        actionText = actionText,
        onActionClick = {
            if (enabled) {
                onActionClick()
            }
        },
        modifier = modifier,
        actionTextColor = if (enabled) {
            White
        } else {
            Grey600
        },
        actionBackgroundColor = if (enabled) {
            Salmon600
        } else {
            Grey100
        },
    ) {
        val allChecked = values.values.all { it }

        CheckListItem(
            checked = allChecked,
            text = allText,
            modifier = Modifier.clickable(onClick = onToggleAllClick),
            iconPainter = painterResource(
                id = if (allChecked) {
                    R.drawable.icon_checked_checkbox
                } else {
                    R.drawable.icon_unchecked_checkbox
                }
            ),
            checkedTint = Color.Unspecified,
            uncheckedTint = Color.Unspecified,
            textStyle = MaterialTheme.typography.paragraph300.merge(
                fontWeight = FontWeight.Medium
            )
        )

        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp),
            color = Grey50,
            thickness = 1.5.dp,
        )

        Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
            values.forEach { entry ->
                CheckListItem(
                    checked = entry.value,
                    text = entry.key,
                    onClick = {
                        onValueClick(entry.key)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                )
            }
        }
    }
}
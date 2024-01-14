package com.mommydndn.app.deprecated.components.modal.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mommydndn.app.data.model.common.ButtonColor
import com.mommydndn.app.data.model.common.ButtonColorType
import com.mommydndn.app.data.model.common.ButtonSizeType
import com.mommydndn.app.data.model.common.MinMaxRange
import com.mommydndn.app.deprecated.components.button.MommyDndnButton
import com.mommydndn.app.deprecated.models.dialog.DialogButton

@Composable
fun DialogButtonsRow(
    buttons: List<DialogButton>
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        buttons.forEach {
            if (buttons.size <= 1) DialogMaxButtonWrapper(it)
            else DialogMinButtonWrapper(it)
        }
    }
}

@Composable
fun DialogMinButtonWrapper(dialogButton: DialogButton) {
    when (dialogButton) {
        is DialogButton.Primary -> MommyDndnButton(
            color = ButtonColor.SALMON,
            colorType = ButtonColorType.FILLED,
            text = dialogButton.title,
            sizeType = ButtonSizeType.LARGE,
            rangeType = MinMaxRange.MIN,
            onClick = { dialogButton.action?.let { it() } }
        )

        is DialogButton.Secondary -> MommyDndnButton(
            color = ButtonColor.SALMON,
            colorType = ButtonColorType.WEAK,
            text = dialogButton.title,
            sizeType = ButtonSizeType.LARGE,
            rangeType = MinMaxRange.MIN,
            onClick = { dialogButton.action?.let { it() } }
        )
    }
}

@Composable
fun DialogMaxButtonWrapper(dialogButton: DialogButton) {
    when (dialogButton) {
        is DialogButton.Primary -> MommyDndnButton(
            color = ButtonColor.SALMON,
            colorType = ButtonColorType.FILLED,
            text = dialogButton.title,
            rangeType = MinMaxRange.MAX,
            onClick = { dialogButton.action?.let { it() } }
        )

        is DialogButton.Secondary -> MommyDndnButton(
            color = ButtonColor.SALMON,
            colorType = ButtonColorType.WEAK,
            text = dialogButton.title,
            rangeType = MinMaxRange.MAX,
            onClick = { dialogButton.action?.let { it() } }
        )
    }
}
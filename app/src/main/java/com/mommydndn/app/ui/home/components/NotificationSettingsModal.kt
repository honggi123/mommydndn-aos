package com.mommydndn.app.ui.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mommydndn.app.R
import com.mommydndn.app.ui.components.modal.CheckListModal
import com.mommydndn.app.ui.components.modal.MommydndnModalBottomSheet
import com.mommydndn.app.ui.theme.White
import kotlinx.coroutines.launch

// TODO
data class NotificationSetting(
    val id: Int,
    val name: String,
)

@Composable
internal fun NotificationSettingsModal(
    notificationSettings: Map<NotificationSetting, Boolean>,
    onToggleAllClick: () -> Unit,
    onNotificationSettingClick: (NotificationSetting) -> Unit,
    onCloseClick: () -> Unit,
    onStartClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    CheckListModal(
        values = notificationSettings
            .map { it.key.name to it.value }
            .toMap(),
        onCloseClick = onCloseClick,
        onActionClick = onStartClick,
        onToggleAllClick = onToggleAllClick,
        onValueClick = { name ->
            notificationSettings.keys
                .find { it.name == name }
                ?.let(onNotificationSettingClick)
        },
        modifier = modifier,
        allText = stringResource(R.string.only_necessary_notifications),
        dismissText = stringResource(id = R.string.close),
        actionText = stringResource(R.string.start),
        enabled = true,
    )
}

@Preview
@Composable
private fun NotificationSettingsModalPreview() {
    var notificationSettings by remember {
        mutableStateOf(notificationSettings.associateWith { true })
    }

    val coroutineScope = rememberCoroutineScope()

    val sheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Expanded)

    MommydndnModalBottomSheet(
        sheetState = sheetState,
        sheetContent = {
            NotificationSettingsModal(
                notificationSettings = notificationSettings,
                onToggleAllClick = {
                    val allChecked = notificationSettings.values.all { it }

                    notificationSettings = notificationSettings
                        .toMutableMap()
                        .mapValues { !allChecked }
                },
                onNotificationSettingClick = { notificationSetting ->
                    notificationSettings = notificationSettings
                        .toMutableMap()
                        .apply { put(notificationSetting, !getValue(notificationSetting)) }
                },
                onCloseClick = {
                    coroutineScope.launch {
                        sheetState.hide()
                    }
                },
                onStartClick = {
                    coroutineScope.launch {
                        sheetState.hide()
                    }
                },
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 32.dp),
            )
        }
    ) {
        Box(
            modifier = Modifier
                .background(White)
                .fillMaxSize(),
        )
    }
}
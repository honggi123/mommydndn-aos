package com.mommydndn.app.ui.home

import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.mommydndn.app.R
import com.mommydndn.app.data.model.notification.Notification
import com.mommydndn.app.deprecated.components.modal.NoticeSettingListModal
import com.mommydndn.app.ui.theme.GreyOpacity400
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun BottomSheetModal(
    scope: CoroutineScope,
    noticeSettings: List<Notification>
) {

    val sheetState = rememberModalBottomSheetState(
        ModalBottomSheetValue.Expanded,
        skipHalfExpanded = true,
        animationSpec = spring(
            dampingRatio = 0.85f,
            stiffness = 100f
        )
    )

    ModalBottomSheetLayout(
        modifier = Modifier,
        sheetState = sheetState,
        sheetContentColor = Color.Transparent,
        sheetBackgroundColor = Color.Transparent,
        scrimColor = GreyOpacity400,
        sheetElevation = 0.dp,
        sheetContent = {}
    ) {
        
    }
    if (noticeSettings.isNotEmpty()) {
        ModalBottomSheetLayout(
            modifier = Modifier,
            sheetState = sheetState,
            sheetContentColor = Color.Transparent,
            sheetBackgroundColor = Color.Transparent,
            scrimColor = GreyOpacity400,
            sheetElevation = 0.dp,
            sheetContent = {
                NoticeSettingListModal(
                    modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 100.dp),
                    onDismiss = { scope.launch { sheetState.hide() } },
                    onItemSelected = { index, isChecked ->
                        // TODO
                    },
                    onComplete = {
                        // TODO
                    },
                    itemList = noticeSettings,
                    titleCheckBoxText = stringResource(id = R.string.send_necessary_notifications)
                )
            }
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Transparent)
            )
        }
    }
}
package com.mommydndn.app.ui.components.modal.layout

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.mommydndn.app.ui.theme.GreyOpacity400


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BaseModalBottomSheetLayout(
    sheetState: ModalBottomSheetState =
        rememberModalBottomSheetState(ModalBottomSheetValue.Hidden),
    sheetContent: @Composable ColumnScope.() -> Unit,
    content: @Composable () -> Unit,
) {

    ModalBottomSheetLayout(
        modifier = Modifier,
        sheetState = sheetState,
        sheetContentColor = Color.Transparent,
        sheetBackgroundColor = Color.Transparent,
        scrimColor = GreyOpacity400,
        sheetElevation = 0.dp,
        sheetContent = {
//            Column(
//                modifier = Modifier.fillMaxWidth()
//            ) {
//                dialogTitle?.let {
//                    DialogTitleWrapper(it)
//                }
//                dialogContent?.let { DialogContentWrapper(it) }
//                buttons?.let { DialogButtonsRow(it) }
//            }
            sheetContent()
        }
    ) {
        content()
    }
}
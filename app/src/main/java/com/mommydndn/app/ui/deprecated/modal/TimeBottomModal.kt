package com.mommydndn.app.ui.deprecated.modal

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.commandiron.wheel_picker_compose.WheelTimePicker
import com.commandiron.wheel_picker_compose.core.TimeFormat
import com.commandiron.wheel_picker_compose.core.WheelPickerDefaults
import com.mommydndn.app.data.model.care.filter.FilterItemsType
import com.mommydndn.app.ui.deprecated.components.box.SelectScopeBox
import com.mommydndn.app.ui.deprecated.components.modal.components.DialogButtonsRow
import com.mommydndn.app.ui.deprecated.components.modal.components.DialogTitleWrapper
import com.mommydndn.app.ui.deprecated.models.dialog.DialogButton
import com.mommydndn.app.ui.deprecated.models.dialog.DialogTitle
import com.mommydndn.app.ui.theme.Grey200
import com.mommydndn.app.ui.theme.Grey50
import com.mommydndn.app.ui.theme.Grey700
import com.mommydndn.app.ui.theme.White
import com.mommydndn.app.ui.theme.paragraph300
import com.mommydndn.app.ui.theme.Shadow700
import com.mommydndn.app.ui.theme.Typography
import com.mommydndn.app.utils.DateTimeUtils

@Composable
fun TimeBottomModal(
    modifier: Modifier = Modifier,
    item: FilterItemsType.Time,
    onClickClose: () -> Unit = {},
    onClickComplete: (FilterItemsType.Time) -> Unit = {}
) {
    var timeItem by remember { mutableStateOf(item) }

    var selectType by remember { mutableStateOf(TimeModalSelectType.START_TIME) }

    var selectedStartTime by remember { mutableStateOf(timeItem.startTime) }
    var selectedEndTime by remember { mutableStateOf(timeItem.endTime) }

    var isStartTimeSeleted by remember { mutableStateOf(true) }
    var isEndTimeSeleted by remember { mutableStateOf(false) }

    Box(
        modifier = modifier
            .wrapContentSize()
            .then(Shadow700)
            .background(color = White, shape = RoundedCornerShape(24.dp)),
        contentAlignment = Alignment.TopCenter
    ) {

        Box(
            modifier = Modifier
                .offset(y = 10.dp)
                .width(64.dp)
                .height(6.dp)
                .background(color = Grey200, shape = RoundedCornerShape(size = 50.dp))
        )

        Column(
            modifier = Modifier
                .wrapContentSize()
                .padding(start = 20.dp, top = 36.dp, end = 20.dp, bottom = 24.dp)
        ) {
            DialogTitleWrapper(DialogTitle.Refresh(text = "시간", refreshAction = {
                timeItem = timeItem.copy(startTime = null, endTime = null)
                selectedStartTime = null
                selectedEndTime = null
            }))

            Divider(
                thickness = 1.5.dp,
                color = Grey50,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Transparent)
                    .padding(vertical = 12.dp)
            )

            Column {
                SelectScopeBox(
                    option1Text = selectedStartTime?.let { DateTimeUtils.getLocalTimeText(it) }
                        ?: "시작시간",
                    option2Text = selectedEndTime?.let { DateTimeUtils.getLocalTimeText(it) }
                        ?: "종료시간",
                    onOption1Clicked = {
                        selectType = TimeModalSelectType.START_TIME
                        isStartTimeSeleted = true
                        isEndTimeSeleted = false
                    },
                    onOption2Clicked = {
                        selectType = TimeModalSelectType.END_TIME
                        isEndTimeSeleted = true
                        isStartTimeSeleted = false
                    },
                    isOption1Selected = isStartTimeSeleted,
                    isOption2Selected = isEndTimeSeleted
                )
            }

            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(28.dp)
            )

            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                WheelTimePicker(
                    timeFormat = TimeFormat.AM_PM,
                    size = DpSize(220.dp, 140.dp),
                    textStyle = Typography.paragraph300.copy(
                        color = Grey700,
                        fontWeight = FontWeight.Bold
                    ),
                    selectorProperties = WheelPickerDefaults.selectorProperties(
                        enabled = true,
                        shape = RoundedCornerShape(16.dp),
                        color = Grey50,
                        border = BorderStroke(0.dp, Grey50)
                    )
                ) { time ->
                    if (selectType == TimeModalSelectType.START_TIME) selectedStartTime = time
                    else if (selectType == TimeModalSelectType.END_TIME) selectedEndTime = time
                }
            }

            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(28.dp)
            )

            DialogButtonsRow(
                listOf(
                    DialogButton.Secondary(title = "닫기", action = {
                        onClickClose()
                        timeItem = item
                    }),
                    DialogButton.Primary(title = "적용하기", action = {
                        onClickComplete(
                            timeItem.copy(startTime = selectedStartTime, endTime = selectedEndTime)
                        )
                    })
                )
            )
        }
    }
}

enum class TimeModalSelectType {
    START_TIME,
    END_TIME
}

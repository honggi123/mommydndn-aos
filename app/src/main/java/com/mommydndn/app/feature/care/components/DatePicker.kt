package com.mommydndn.app.feature.care.components

import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import java.time.Instant
import java.time.LocalDate
import java.time.LocalTime
import java.time.ZoneId
import java.time.ZonedDateTime

@Composable
internal fun PostDatePicker(
    onDateSelected: (LocalDate) -> Unit,
    onDismissClick: () -> Unit,
    modifier: Modifier = Modifier,
    datePickerState: DatePickerState = rememberDatePickerState(),
) {
    DatePickerDialog(
        onDismissRequest = {
            onDismissClick()
        },
        confirmButton = {
            TextButton(
                onClick = {
                    val selectedDateMillis = datePickerState.selectedDateMillis

                    if (selectedDateMillis != null) {
                        val selectedDate = Instant.ofEpochMilli(selectedDateMillis)
                            .atZone(ZoneId.systemDefault())
                            .toLocalDate()

                        onDateSelected(selectedDate)

                        onDismissClick()
                    }
                }
            ) {
                Text("확인")
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    onDismissClick()
                }
            ) {
                Text("취소")
            }
        },
        modifier = modifier,
    ) {
        DatePicker(
            state = datePickerState,
            // todo: 선택 가능한 날짜 정책에 따라 유효성 검사 로직 변경
            dateValidator = { selectedDateMillis ->
                val midNight = ZonedDateTime.of(
                    LocalDate.now(),
                    LocalTime.MIDNIGHT,
                    ZoneId.systemDefault()
                )

                selectedDateMillis >= midNight.toInstant().toEpochMilli()
            },
            title = null,
            headline = null,
            showModeToggle = false,
        )
    }
}

@Preview
@Composable
private fun DatePicker_Preview() {
    PostDatePicker(
        onDateSelected = {},
        onDismissClick = {},
        modifier = Modifier.wrapContentSize(),
    )
}
package com.mommydndn.app.ui.models.dialog

import com.mommydndn.app.data.model.care.CaringTypeItem
import com.mommydndn.app.data.model.common.DayOfWeekItem

sealed class DialogContent {
    data class Radio(
        val list: List<SelectableItem>
    ) : DialogContent()

    data class Check(
        val list: List<SelectableItem>
    ) : DialogContent()

    data class Days(
        val list: List<DayOfWeekItem>
    ) : DialogContent()

    data class Time(
        val startTimeText: String?,
        val endTimeText: String?,
        val onSelectStartTime: () -> Unit,
        val onSelectEndTime: () -> Unit
    ) : DialogContent()
}

data class SelectableItem(
    val text: String,
    val isSelected: Boolean
)
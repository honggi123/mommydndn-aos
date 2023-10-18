package com.mommydndn.app.data.model.care

enum class WorkHoursType(val value: String) {
    SHORT("단기"),
    REGULAR("정기")
}

data class WorkHoursTypeItem(
    val workHoursType: WorkHoursType,
    var isSelected: Boolean = false
)

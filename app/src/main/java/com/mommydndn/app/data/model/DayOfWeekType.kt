package com.mommydndn.app.data.model

enum class DayOfWeekType(val displayingName: String) {
    MONDAY("월"),
    TUESDAY("화"),
    WEDNESDAY("수"),
    THURSDAY("목"),
    FRIDAY("금"),
    SATURDAY("토"),
    SUNDAY("일")
}

data class DayOfWeekItem(
    val type: DayOfWeekType,
    var isSelected: Boolean = false
)
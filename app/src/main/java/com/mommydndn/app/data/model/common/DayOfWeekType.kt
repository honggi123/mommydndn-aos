package com.mommydndn.app.data.model.common

enum class DayOfWeekType(val displayingName: String) {
    MON("월"),
    TUE("화"),
    WED("수"),
    THU("목"),
    FRI("금"),
    SAT("토"),
    SUN("일")
}

data class DayOfWeekItem(
    val type: DayOfWeekType,
    var isSelected: Boolean = false
)
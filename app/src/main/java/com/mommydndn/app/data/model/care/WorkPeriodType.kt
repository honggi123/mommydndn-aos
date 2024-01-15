package com.mommydndn.app.data.model.care

enum class WorkPeriodType(val value: String,var isSelected: Boolean = false) {
    ONETIME("1회성"),
    REGULAR("정기")
}

data class WorkPeriodTypeItem(
    val workPeriodType: WorkPeriodType?,
    var isSelected: Boolean = false
)


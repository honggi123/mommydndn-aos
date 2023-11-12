package com.mommydndn.app.data.model.care

enum class SortingType(
    val diaplayingName: String,
    var isSelected: Boolean = false
) {
    LATEST("최신순"),
    MOST_VIEW("조회 많은 순"),
    HIGHEST_SALARY("급여 많은 순"),
    CLOSEST("가까운 순")
}

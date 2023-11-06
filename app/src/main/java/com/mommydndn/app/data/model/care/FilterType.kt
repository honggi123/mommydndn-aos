package com.mommydndn.app.data.model.care

enum class FilterType {
    SORTING,
    CARING,
    NEIGHBORHOODSCOPE,
    PERIOD,
    TIME
}

data class FilterItem(
    val type: FilterType,
    val displayingName: String,
    val isSelected: Boolean
)
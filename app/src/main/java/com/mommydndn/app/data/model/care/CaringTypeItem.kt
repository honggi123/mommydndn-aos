package com.mommydndn.app.data.model.care

data class CaringTypeItem(
    val caringType: CaringType,
    val displayName: String,
    var isSelected: Boolean = false
)
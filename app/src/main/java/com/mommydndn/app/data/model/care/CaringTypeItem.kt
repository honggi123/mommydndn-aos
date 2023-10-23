package com.mommydndn.app.data.model.care

data class CaringTypeItem(
    val caringTypeId: Int,
    val caringType: CaringType,
    val displayName: String,
    var isSelected: Boolean = false
)
package com.mommydndn.app.data.model.care

data class EtcCheckItem(
    val displayName: String,
    val id: Int,
    val conditionCode: String,
    val isChecked: Boolean = false
)

package com.mommydndn.app.data.model.care

import kotlinx.serialization.Serializable

data class EtcCheckItem(
    val displayName: String,
    val id: Int,
    val conditionCode: String,
    val isChecked: Boolean = false
)

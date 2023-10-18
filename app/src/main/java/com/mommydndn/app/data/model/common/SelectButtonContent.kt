package com.mommydndn.app.data.model.common

data class SelectButtonContent(
    val isSelected: Boolean = false,
    val text: String,
    val onClick: (Boolean) -> Unit
)

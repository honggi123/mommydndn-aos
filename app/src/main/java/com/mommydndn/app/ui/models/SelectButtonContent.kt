package com.mommydndn.app.ui.models

data class SelectButtonContent(
    val isSelected: Boolean = false,
    val text: String,
    val onClick: (Boolean) -> Unit
)

package com.mommydndn.app.ui.deprecated.models.dialog

sealed class DialogTitle(
    open val text: String
) {
    data class Default(
        override val text: String
    ) : DialogTitle(text)

    data class Refresh(
        override val text: String,
        val refreshAction: () -> Unit
    ) : DialogTitle(text)

    data class Check(
        override val text: String,
        val isChecked: Boolean,
        val checkAction: (Boolean) -> Unit
    ) : DialogTitle(text)

    data class Location(
        override val text: String,
        val locationText: String,
        val action: () -> Unit
    ) : DialogTitle(text)
}

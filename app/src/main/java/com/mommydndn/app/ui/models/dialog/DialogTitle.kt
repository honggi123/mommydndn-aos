package com.mommydndn.app.ui.models.dialog

sealed class DialogTitle(
    open val text: String
) {
    data class Default(
        override val text: String
    ) : DialogTitle(text)

    data class Refresh(
        override val text: String,
        val action: () -> Unit
    ) : DialogTitle(text)

    data class Check(
        override val text: String,
        val action: () -> Unit
    ) : DialogTitle(text)

    data class Location(
        override val text: String,
        val locationText: String,
        val action: () -> Unit
    ) : DialogTitle(text)
}
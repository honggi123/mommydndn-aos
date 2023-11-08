package com.mommydndn.app.ui.models.dialog

sealed class DialogButton(
    open val title: String,
    open val action: (() -> Unit)?
) {
    data class Primary(
        override val title: String,
        override val action: (() -> Unit)? = null
    ) : DialogButton(title, action)

    data class Secondary(
        override val title: String,
        override val action: (() -> Unit)? = null
    ) : DialogButton(title, action)

}
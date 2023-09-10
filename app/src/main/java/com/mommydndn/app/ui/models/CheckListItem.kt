package com.mommydndn.app.ui.models

sealed class CheckListItem(
    open val title: String
) {
    data class CheckBox(
        override val title: String
    ) : CheckListItem(title)

    data class CheckMark(
        override val title: String
    ) : CheckListItem(title)
}

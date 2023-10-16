package com.mommydndn.app.ui.models

import android.net.Uri
import java.net.URI

sealed class ImageInputFieldType {
    data class Add(
        val index: Int,
        val onClick: (() -> Unit)? = null
    ) : ImageInputFieldType()

    data class Editable(
        val imageUri: Uri
    ) : ImageInputFieldType()

    data class Ineditable(
        val imageUri: Uri
    ) : ImageInputFieldType()
}
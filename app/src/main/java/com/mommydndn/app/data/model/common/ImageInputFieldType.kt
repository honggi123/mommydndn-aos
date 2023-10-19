package com.mommydndn.app.data.model.common

import android.net.Uri

sealed class ImageInputFieldType {
    data class Add(
        val index: Int,
        val onClick: (() -> Unit)? = null
    ) : ImageInputFieldType()

    data class Editable(
        val imageUri: Uri,
        val onRemoveClick: (() -> Unit)? = null
    ) : ImageInputFieldType()

    data class Ineditable(
        val imageUri: Uri
    ) : ImageInputFieldType()
}
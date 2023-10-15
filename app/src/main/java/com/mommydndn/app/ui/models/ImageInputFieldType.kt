package com.mommydndn.app.ui.models

import java.net.URI

sealed class ImageInputFieldType {
    data class Add(
        val index: Int,
        val onClick: (() -> Unit)? = null
    ) : ImageInputFieldType()

    data class Editable(
        val imageUri: URI
    ) : ImageInputFieldType()

    data class Ineditable(
        val imageUri: URI
    ) : ImageInputFieldType()
}
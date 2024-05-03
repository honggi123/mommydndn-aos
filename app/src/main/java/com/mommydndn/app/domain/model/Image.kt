package com.mommydndn.app.domain.model

data class Image(
    val id: String,
    val url: String
) {
    constructor() : this(
        id = "",
        url = ""
    )
}
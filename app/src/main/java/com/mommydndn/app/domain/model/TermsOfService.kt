package com.mommydndn.app.domain.model

data class TermsOfService(
    val id: Int,
    val name: String,
    val required: Boolean,
    val url: String,
)

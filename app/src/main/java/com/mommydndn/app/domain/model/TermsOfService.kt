package com.mommydndn.app.domain.model

data class TermsOfService(
    val id: String,
    val title: String,
    val url: String,
    val isRequired: Boolean,
)
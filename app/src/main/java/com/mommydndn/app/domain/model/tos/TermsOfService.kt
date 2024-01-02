package com.mommydndn.app.domain.model.tos

data class TermsOfService(
    val id: Int,
    val name: String,
    val url: String,
    val isRequired: Boolean,
)

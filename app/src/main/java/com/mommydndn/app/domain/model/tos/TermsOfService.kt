package com.mommydndn.app.domain.model.tos

data class TermsOfService(
    val isRequired: Boolean,
    val name: String,
    val id: Int,
    val url: String,
    val isApproved: Boolean
)

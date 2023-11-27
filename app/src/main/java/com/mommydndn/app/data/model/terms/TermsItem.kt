package com.mommydndn.app.data.model.terms

data class TermsItem(
    val createdAt: Long,
    val isRequired: Boolean,
    val name: String,
    val termsId: Int,
    val updateAt: Long,
    val url: String,
    val isSelected: Boolean = false
)

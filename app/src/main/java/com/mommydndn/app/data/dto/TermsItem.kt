package com.mommydndn.app.data.dto

data class TermsItem(
    val createdAt: Long,
    val isRequired: Boolean,
    val name: String,
    val termsId: Int,
    val updateAt: Long,
    val url: String
)
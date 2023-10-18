package com.mommydndn.app.data.api.model.response

import kotlinx.serialization.Serializable

@Serializable
data class TermsItemResponse(
    val createdAt: Long,
    val isRequired: Boolean,
    val name: String,
    val termsId: Int,
    val updateAt: Long,
    val url: String
)
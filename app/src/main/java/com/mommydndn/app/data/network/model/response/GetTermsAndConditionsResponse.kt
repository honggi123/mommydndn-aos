package com.mommydndn.app.data.network.model.response

import kotlinx.serialization.Serializable

typealias GetTermsAndConditionsResponse = List<GetTermsAndConditions>

@Serializable
data class GetTermsAndConditions(
    val createdAt: Long,
    val isRequired: Boolean,
    val name: String,
    val termsId: Int,
    val updateAt: Long,
    val url: String
)
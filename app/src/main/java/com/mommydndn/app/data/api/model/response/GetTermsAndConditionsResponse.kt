package com.mommydndn.app.data.api.model.response

import kotlinx.serialization.Serializable

typealias GetTermsAndConditionsResponse = List<GetTermsAndConditions>

@Serializable
data class GetTermsAndConditions(
    val isRequired: Boolean,
    val name: String,
    val termsId: Int,
    val url: String
)
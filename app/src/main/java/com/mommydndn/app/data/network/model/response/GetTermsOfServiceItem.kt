package com.mommydndn.app.data.network.model.response

import kotlinx.serialization.Serializable

typealias GetTermsOfServiceResponse = List<GetTermsOfServiceItem>

@Serializable
data class GetTermsOfServiceItem(
    val termsId: Int,
    val name: String,
    val url: String,
    val isRequired: Boolean,
)
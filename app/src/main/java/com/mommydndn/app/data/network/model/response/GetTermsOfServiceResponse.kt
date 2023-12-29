package com.mommydndn.app.data.api.model.response

import com.mommydndn.app.domain.model.tos.TermsOfService
import kotlinx.serialization.Serializable

typealias GetTermsOfServiceListResponse = List<GetTermsOfServiceResponse>

@Serializable
data class GetTermsOfServiceResponse(
    val isRequired: Boolean,
    val name: String,
    val termsId: Int,
    val url: String
)

fun GetTermsOfServiceResponse.toDomain(): TermsOfService =
    TermsOfService(
        isRequired = isRequired,
        name = name,
        id = termsId,
        url = url
    )
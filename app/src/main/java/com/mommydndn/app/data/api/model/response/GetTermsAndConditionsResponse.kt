package com.mommydndn.app.data.api.model.response

import com.mommydndn.app.domain.model.TermsAndConditions.TermsAndConditionsItem
import kotlinx.serialization.Serializable

typealias GetTermsAndConditionsResponse = List<GetTermsAndConditions>

@Serializable
data class GetTermsAndConditions(
    val isRequired: Boolean,
    val name: String,
    val termsId: Int,
    val url: String
)

fun GetTermsAndConditions.toDomain(): TermsAndConditionsItem =
    TermsAndConditionsItem(
        isRequired = isRequired,
        name = name,
        termsId = termsId,
        url = url
    )
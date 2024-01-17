package com.mommydndn.app.data.network.service.request

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class UpdateTermsOfServiceAgreementRequest(
    @SerializedName("termsId")
    val id: Long,
    val isApproved: Boolean
)
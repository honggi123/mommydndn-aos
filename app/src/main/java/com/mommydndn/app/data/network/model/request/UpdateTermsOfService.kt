package com.mommydndn.app.data.network.model.request

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

typealias UpdateTermsOfServiceRequest = List<UpdateTermsOfService>

@Serializable
data class UpdateTermsOfService(
    @SerializedName("termsId")
    val termsId: Int,
    @SerializedName("isApproved")
    val isApproved: Boolean
)
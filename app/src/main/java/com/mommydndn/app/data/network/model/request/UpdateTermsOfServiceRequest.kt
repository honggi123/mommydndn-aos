package com.mommydndn.app.data.network.model.request

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable


typealias UpdateTermsOfServiceListRequest = List<UpdateTermsOfServiceRequest>

@Serializable
data class UpdateTermsOfServiceRequest(
    @SerializedName("termsId")
    val termsId: Int,
    @SerializedName("isApproved")
    val isApproved: Boolean
)
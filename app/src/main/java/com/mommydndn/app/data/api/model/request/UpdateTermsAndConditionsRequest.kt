package com.mommydndn.app.data.api.model.request

import com.google.gson.annotations.SerializedName
import com.mommydndn.app.data.api.model.response.GetTermsAndConditions
import kotlinx.serialization.Serializable


typealias UpdateTermsAndConditionsRequest = List<UpdateTermsAndConditions>

@Serializable
data class UpdateTermsAndConditions(
    @SerializedName("termsId")
    val termsId: Int,
    @SerializedName("isApproved")
    val isApproved: Boolean
)
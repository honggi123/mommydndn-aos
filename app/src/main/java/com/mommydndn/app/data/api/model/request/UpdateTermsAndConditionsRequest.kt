package com.mommydndn.app.data.api.model.request

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable


@Serializable
data class UpdateTermsAndConditions(
    @SerializedName("termsId")
    val termsId: Int,
    @SerializedName("isApproved")
    val isApproved: Boolean
)
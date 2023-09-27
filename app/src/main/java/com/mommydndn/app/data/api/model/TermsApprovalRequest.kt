package com.mommydndn.app.data.api.model

import com.google.gson.annotations.SerializedName

data class TermsApprovalRequest(
    @SerializedName("termsId")
    val termsId: Int,
    @SerializedName("isApproved")
    val isApproved: Boolean
)
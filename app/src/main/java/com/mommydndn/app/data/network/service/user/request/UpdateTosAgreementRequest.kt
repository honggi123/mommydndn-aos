package com.mommydndn.app.data.network.service.user.request

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class UpdateTosAgreementRequest(
    @SerializedName("termsId")
    val id: Int,
    val isApproved: Boolean
)
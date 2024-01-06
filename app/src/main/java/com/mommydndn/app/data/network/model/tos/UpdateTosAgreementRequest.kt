package com.mommydndn.app.data.network.model.tos

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class UpdateTosAgreementRequest(
    @SerializedName("termsId")
    val id: Int,
    val isApproved: Boolean
)
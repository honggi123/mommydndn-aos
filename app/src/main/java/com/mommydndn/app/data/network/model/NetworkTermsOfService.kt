package com.mommydndn.app.data.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NetworkTermsOfService(
    @SerialName("termsId")
    val id: Int,
    val name: String,
    val url: String,
    val isRequired: Boolean,
)
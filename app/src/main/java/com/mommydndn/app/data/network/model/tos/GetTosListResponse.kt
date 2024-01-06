package com.mommydndn.app.data.network.model.tos

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

typealias GetTosListResponse = List<TosResponse>

@Serializable
data class TosResponse(
    @SerialName("termsId")
    val id: Int,
    val name: String,
    val url: String,
    val isRequired: Boolean,
)
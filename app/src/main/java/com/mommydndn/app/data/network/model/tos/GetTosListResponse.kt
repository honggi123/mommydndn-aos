package com.mommydndn.app.data.network.model.tos

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

typealias GetTosListResponse = List<TosApiModel>

@Serializable
data class TosApiModel(
    @SerialName("termsId")
    val id: Int,
    val name: String,
    val url: String,
    val isRequired: Boolean,
)
package com.mommydndn.app.data.network.model.care.response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreateAgencyCareProviderResponse(
    @SerialName("companyId")
    val id: Int
)
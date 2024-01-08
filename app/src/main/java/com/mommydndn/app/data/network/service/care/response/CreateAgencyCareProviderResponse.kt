package com.mommydndn.app.data.network.service.care.response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreateAgencyCareProviderResponse(
    @SerialName("companyId")
    val id: Int
)
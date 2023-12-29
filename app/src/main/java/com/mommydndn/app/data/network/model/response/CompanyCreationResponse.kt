package com.mommydndn.app.data.network.model.response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CompanyCreationResponse(
    @SerialName("companyId")
    val companyId: Int
)
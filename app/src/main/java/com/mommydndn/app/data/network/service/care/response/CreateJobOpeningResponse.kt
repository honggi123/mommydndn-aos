package com.mommydndn.app.data.network.service.care.response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreateJobOpeningResponse(
    @SerialName("jobOfferId")
    val id: Int
)
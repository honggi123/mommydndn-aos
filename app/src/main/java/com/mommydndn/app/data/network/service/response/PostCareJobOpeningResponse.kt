package com.mommydndn.app.data.network.service.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

// TODO
@Serializable
data class PostCareJobOpeningResponse(
    @SerialName("jobOfferId")
    val id: Int
)
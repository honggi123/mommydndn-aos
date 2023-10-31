package com.mommydndn.app.data.api.model.response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreateJobOfferResponse(
    @SerialName("jobOfferId")
    val jobOfferId: Int
)
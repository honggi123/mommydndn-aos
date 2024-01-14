package com.mommydndn.app.data.network.service.location.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
@Serializable
data class LocationApiModel(
    @SerialName("name")
    val name: String,
    @SerialName("sigName")
    val sigName: String,
    @SerialName("ctprvnName")
    val ctprvnName: String,
    val id: Int,
    val fullName: String
)

fun LocationApiModel.displayName(): String {
    return this.fullName
}


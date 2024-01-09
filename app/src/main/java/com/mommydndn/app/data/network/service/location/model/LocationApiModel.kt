package com.mommydndn.app.data.network.service.location.model

import kotlinx.serialization.Serializable
@Serializable
data class LocationApiModel(
    val id: Int,
    val name: String,
    val sigName: String,
    val ctprvnName: String,
    val fullName: String
)

fun LocationApiModel.displayName(): String {
    return this.fullName
}


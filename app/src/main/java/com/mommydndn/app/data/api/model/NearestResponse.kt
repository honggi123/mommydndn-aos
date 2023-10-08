package com.mommydndn.app.data.api.model

import kotlinx.serialization.Serializable

@Serializable
data class EmdItem(
    val id: Int,
    val name: String,
    val sigName: String,
    val ctprvnName: String,
    val fullName: String
)

@Serializable
data class NearestResponse(
    val emdList: List<EmdItem>
)
fun EmdItem.displayName(): String {
    return this.fullName
}

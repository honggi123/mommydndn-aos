package com.mommydndn.app.data.model

import kotlinx.serialization.Serializable

@Serializable
data class EmdItem(
    val id: Int,
    val name: String,
    val sigName: String,
    val ctprvnName: String,
    val fullName: String
)

fun EmdItem.displayName(): String {
    return this.fullName
}
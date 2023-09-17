package com.mommydndn.app.data.model

data class EmdItem(
    val id: Int,
    val name: String,
    val sigName: String,
    val ctprvnName: String,
    val fullName: String
)

data class NearestResponse(
    val emdList: List<EmdItem>
)

fun EmdItem.displayName(): String {
    return this.fullName
}

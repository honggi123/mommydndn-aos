package com.mommydndn.app.data.network.model

import kotlinx.serialization.Serializable

@Serializable
data class NetworkNeighborhood(
    val id: Int,
    val name: String,
    // 시, 도
    val ctprvnName: String,
    // 시, 군, 구
    val sigName: String,
    val fullName: String,
)
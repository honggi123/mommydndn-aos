package com.mommydndn.app.data.network.feature.common.model

import com.mommydndn.app.domain.model.location.Neighborhood
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

fun LocationApiModel.toDomain() : Neighborhood {
    return Neighborhood(
        id = id,
        name = name,
        address = fullName
    )
}

package com.mommydndn.app.data.network.model.response

import com.mommydndn.app.domain.model.location.Neighborhood
import kotlinx.serialization.Serializable

@Serializable
data class GetEmdItemResponse(
    val id: Int,
    val name: String,
    val sigName: String,
    val ctprvnName: String,
    val fullName: String
)

fun GetEmdItemResponse.toDomain() : Neighborhood {
    return Neighborhood(
        id = id,
        name = name,
        address = fullName
    )
}


package com.mommydndn.app.data.api.model.response

import com.mommydndn.app.domain.model.location.LocationInfo
import kotlinx.serialization.Serializable

@Serializable
data class GetEmdItemResponse(
    val id: Int,
    val name: String,
    val sigName: String,
    val ctprvnName: String,
    val fullName: String
)

fun GetEmdItemResponse.toDomain() : LocationInfo {
    return LocationInfo(
        id = id,
        name = name,
        sigName = sigName,
        ctprvnName = ctprvnName,
        fullName = fullName
    )
}


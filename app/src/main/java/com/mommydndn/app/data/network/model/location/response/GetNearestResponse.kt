package com.mommydndn.app.data.network.model.location.response

import com.mommydndn.app.domain.model.location.Neighborhood
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetNearestNeighborhoodListResponse(
    @SerialName("emdList")
    val neighborhoodList: List<EmdItemResponse>
)
@Serializable
data class EmdItemResponse(
    val id: Int,
    val name: String,
    val sigName: String,
    val ctprvnName: String,
    val fullName: String
)

fun EmdItemResponse.toDomain() : Neighborhood {
    return Neighborhood(
        id = id,
        name = name,
        address = fullName
    )
}

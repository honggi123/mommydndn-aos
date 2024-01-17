package com.mommydndn.app.data.network.service.response

import com.mommydndn.app.data.network.model.NetworkCareType
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

typealias GetCareTypesResponse = List<GetCareType>

@Serializable
data class GetCareType(
    @SerialName("caringTypeId")
    val id: Long,
    @SerialName("caringTypeCode")
    val type: NetworkCareType,
    val displayName: String,
)
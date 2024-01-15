package com.mommydndn.app.data.network.service.care.response


import com.mommydndn.app.data.network.model.NetworkCareType
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

typealias GetCareTypeListResponse = List<CareTypeResponseApiModel>

@Serializable
data class CareTypeResponseApiModel(
    @SerialName("caringTypeId")
    val id: Int,
    @SerialName("caringTypeCode")
    val careType: NetworkCareType,
    val displayName: String
)
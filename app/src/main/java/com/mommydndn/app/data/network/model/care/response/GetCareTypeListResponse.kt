package com.mommydndn.app.data.network.model.care.response


import com.mommydndn.app.data.model.care.CaringType
import com.mommydndn.app.data.model.care.CaringTypeSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

typealias GetCareTypeListResponse = List<CareTypeResponse>

@Serializable
data class CareTypeResponse(
    @Serializable(with = CaringTypeSerializer::class)
    @SerialName("caringTypeCode")
    val careTypeCode: CaringType,
    @SerialName("caringTypeId")
    val careTypeId: Int,
    val displayName: String
)
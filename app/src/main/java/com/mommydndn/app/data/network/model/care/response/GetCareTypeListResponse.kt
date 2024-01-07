package com.mommydndn.app.data.network.model.care.response


import com.mommydndn.app.data.model.care.CaringType
import com.mommydndn.app.data.model.care.CaringTypeSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

typealias GetCareTypeListResponse = List<CareTypeApiModel>

@Serializable
data class CareTypeApiModel(
    @SerialName("caringTypeId")
    val id: Int,
    @Serializable(with = CaringTypeSerializer::class)
    @SerialName("caringTypeCode")
    val careType: CaringType,
    val displayName: String
)
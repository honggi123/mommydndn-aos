package com.mommydndn.app.data.api.model.response


import com.mommydndn.app.data.model.care.CaringType
import com.mommydndn.app.data.model.care.CaringTypeSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CaringTypeResponse(
    @Serializable(with = CaringTypeSerializer::class)
    @SerialName("caringTypeCode")
    val caringTypeCode: CaringType,
    @SerialName("caringTypeId")
    val caringTypeId: Int,
    @SerialName("displayName")
    val displayName: String
)
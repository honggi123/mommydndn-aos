package com.mommydndn.app.data.network.model.care.response


import com.mommydndn.app.data.model.care.CaringType
import com.mommydndn.app.data.model.care.CaringTypeSerializer
import com.mommydndn.app.data.network.model.care.CareTypeApiModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

typealias GetCareTypeListResponse = List<CareTypeResponseApiModel>

@Serializable
data class CareTypeResponseApiModel(
    @SerialName("caringTypeId")
    val id: Int,
    @SerialName("caringTypeCode")
    val careType: CareTypeApiModel,
    val displayName: String
)
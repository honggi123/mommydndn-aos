package com.mommydndn.app.data.network.feature.care.response


import com.mommydndn.app.data.network.feature.care.model.CareTypeApiModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

typealias GetCareTypeListResponse = List<com.mommydndn.app.data.network.feature.care.response.CareTypeResponseApiModel>

@Serializable
data class CareTypeResponseApiModel(
    @SerialName("caringTypeId")
    val id: Int,
    @SerialName("caringTypeCode")
    val careType: CareTypeApiModel,
    val displayName: String
)
package com.mommydndn.app.data.network.feature.care.response

import com.mommydndn.app.data.network.feature.care.model.IndividualOtherConditionApiModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

typealias GetOtherIndividualConditionListResponse = List<com.mommydndn.app.data.network.feature.care.response.OtherIndividualConditionApiModel>

@Serializable
data class OtherIndividualConditionApiModel(
    @SerialName("indOtherConditionId")
    val id: Int,
    @SerialName("indOtherConditionCode")
    val otherCondition: IndividualOtherConditionApiModel,
    val displayName: String
)
package com.mommydndn.app.data.network.feature.care.response

import com.mommydndn.app.data.network.feature.care.model.AgencyOtherConditionApiModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

typealias GetOtherAgenyConditionListResponse = List<com.mommydndn.app.data.network.feature.care.response.OtherAgenyConditionApiModel>

@Serializable
data class OtherAgenyConditionApiModel(
    @SerialName("comOtherConditionId")
    val id: Int,
    @SerialName("comOtherConditionCode")
    val otherCondition: AgencyOtherConditionApiModel,
    val displayName: String
)
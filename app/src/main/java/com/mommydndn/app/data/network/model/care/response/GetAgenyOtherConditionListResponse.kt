package com.mommydndn.app.data.network.model.care.response

import com.mommydndn.app.data.network.model.care.AgencyOtherConditionApiModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

typealias GetOtherAgenyConditionListResponse = List<OtherAgenyConditionApiModel>

@Serializable
data class OtherAgenyConditionApiModel(
    @SerialName("comOtherConditionId")
    val id: Int,
    @SerialName("comOtherConditionCode")
    val otherCondition: AgencyOtherConditionApiModel,
    val displayName: String
)
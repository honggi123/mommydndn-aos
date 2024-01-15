package com.mommydndn.app.data.network.service.care.response

import com.mommydndn.app.data.network.service.care.model.NetworkWorkerOtherCondition
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

typealias GetOtherIndividualConditionListResponse = List<com.mommydndn.app.data.network.service.care.response.OtherIndividualConditionApiModel>

@Serializable
data class OtherIndividualConditionApiModel(
    @SerialName("indOtherConditionId")
    val id: Int,
    @SerialName("indOtherConditionCode")
    val otherCondition: NetworkWorkerOtherCondition,
    val displayName: String
)
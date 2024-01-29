package com.mommydndn.app.data.network.service.response

import com.mommydndn.app.data.network.model.NetworkWorkerOtherCondition
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

typealias GetCareWorkerOtherConditionsResponse = List<GetCareWorkerOtherCondition>

@Serializable
data class GetCareWorkerOtherCondition(
    @SerialName("indOtherConditionId")
    val id: Int,
    @SerialName("indOtherConditionCode")
    val otherCondition: NetworkWorkerOtherCondition,
    val displayName: String,
)
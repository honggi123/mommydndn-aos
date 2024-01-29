package com.mommydndn.app.data.network.service.response

import com.mommydndn.app.data.network.model.NetworkAgencyOtherCondition
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

typealias GetCareAgencyOtherConditionsResponse = List<GetCareAgencyOtherCondition>

@Serializable
data class GetCareAgencyOtherCondition(
    @SerialName("comOtherConditionId")
    val id: Int,
    @SerialName("comOtherConditionCode")
    val otherCondition: NetworkAgencyOtherCondition,
    val displayName: String,
)
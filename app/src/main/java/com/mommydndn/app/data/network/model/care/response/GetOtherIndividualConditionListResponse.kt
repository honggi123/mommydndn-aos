package com.mommydndn.app.data.network.model.care.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

typealias GetOtherIndividualConditionListResponse = List<OtherIndividualConditionResponse>

@Serializable
data class OtherIndividualConditionResponse(
    @SerialName("indOtherConditionId")
    val id: Int,
    @SerialName("indOtherConditionCode")
    val indOtherConditionCode: String,
    val displayName: String
)
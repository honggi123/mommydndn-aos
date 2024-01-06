package com.mommydndn.app.data.network.model.care.response

import com.mommydndn.app.data.network.model.care.request.GetJobOpeningRequest
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

typealias GetOtherAgenyConditionListResponse = List<OtherAgenyConditionResponse>

@Serializable
data class OtherAgenyConditionResponse(
    @SerialName("comOtherConditionId")
    val id: Int,
    @SerialName("comOtherConditionCode")
    val comOtherConditionCode: String,
    val displayName: String
)
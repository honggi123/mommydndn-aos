package com.mommydndn.app.data.network.model.care.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

typealias GetOtherIndividualConditionListResponse = List<OtherIndividualConditionApiModel>

@Serializable
data class OtherIndividualConditionApiModel(
    @SerialName("indOtherConditionId")
    val id: Int,
    @SerialName("indOtherConditionCode")
    val indiviudalOtherConditionCode: String, // TODO change enum
    val displayName: String
)
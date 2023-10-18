package com.mommydndn.app.data.api.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class IndividualEtcCheckItem(
    @SerialName("displayName")
    val displayName: String,
    @SerialName("indOtherConditionCode")
    val indOtherConditionCode: String,
    @SerialName("indOtherConditionId")
    val indOtherConditionId: Int,
)
@Serializable
data class CompanyEtcCheckItem(
    @SerialName("comOtherConditionCode")
    val comOtherConditionCode: String,
    @SerialName("comOtherConditionId")
    val comOtherConditionId: Int,
    @SerialName("displayName")
    val displayName: String
)
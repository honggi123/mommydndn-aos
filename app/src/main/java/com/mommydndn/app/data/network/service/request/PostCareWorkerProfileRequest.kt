package com.mommydndn.app.data.network.service.request

import com.mommydndn.app.data.network.model.NetworkCareType
import com.mommydndn.app.data.network.model.NetworkNeighborhood
import com.mommydndn.app.data.network.model.NetworkPayPeriod
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PostCareWorkerProfileRequest(
    @SerialName("indOtherConditionIdList")
    val otherConditions: List<Int>,
    @SerialName("introLine")
    val introduction: String,
    @SerialName("salaryTypeCode")
    val salaryType: NetworkPayPeriod,
    @SerialName("emd")
    val workingLocation: NetworkNeighborhood,
    @SerialName("caringTypeCodeList")
    val careTypes: List<NetworkCareType>,
    val salary: Int,
    val latitude: Double?,
    val longitude: Double?
)
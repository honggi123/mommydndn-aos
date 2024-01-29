package com.mommydndn.app.data.network.service.request

import com.mommydndn.app.data.network.model.NetworkCareType
import com.mommydndn.app.data.network.model.NetworkNeighborhood
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PostCareAgencyProfileRequest(
    @SerialName("comOtherConditionIdList")
    val agencyOtherConditionIdList: List<Int>,
    @SerialName("introLine")
    val introduction: String,
    @SerialName("minMonthlySalary")
    val minSalary: Int,
    @SerialName("maxMonthlySalary")
    val maxSalary: Int,
    @SerialName("emd")
    val neighborhood: NetworkNeighborhood,
    @SerialName("caringTypeCodeList")
    val careTypes: List<NetworkCareType>,
    val coverImageIdList: List<Int>,
    val commission: Int,
    val latitude: Double?,
    val longitude: Double?,
)
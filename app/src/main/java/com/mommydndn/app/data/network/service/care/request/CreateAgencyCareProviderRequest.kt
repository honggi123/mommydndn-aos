package com.mommydndn.app.data.network.service.care.request

import com.mommydndn.app.data.network.service.care.model.CareTypeApiModel
import com.mommydndn.app.data.network.service.common.model.LocationApiModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreateAgencyCareProviderRequest(
    @SerialName("emd")
    val workingNeighborhood: LocationApiModel,
    @SerialName("comOtherConditionIdList")
    val agencyOtherConditionIdList: List<Int>,
    @SerialName("introLine")
    val introduction: String,
    @SerialName("minMonthlySalary")
    val minSalary: Int,
    @SerialName("maxMonthlySalary")
    val maxSalary: Int,
    @SerialName("caringTypeCodeList")
    val careTypeList: List<CareTypeApiModel>,
    val coverImageIdList: List<Int>,
    val commission: Int,
    val latitude: Double?,
    val longitude: Double?,
)
package com.mommydndn.app.data.network.service.care.request

import com.mommydndn.app.data.network.service.care.model.CareTypeApiModel
import com.mommydndn.app.data.network.service.location.model.LocationApiModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreateAgencyCareProviderRequest(
    @SerialName("comOtherConditionIdList")
    val agencyOtherConditionIdList: List<Int>,
    @SerialName("introLine")
    val introduction: String,
    @SerialName("minMonthlySalary")
    val minSalary: Int,
    @SerialName("maxMonthlySalary")
    val maxSalary: Int,
    @SerialName("emd")
    val workingLocation: LocationApiModel,
    @SerialName("caringTypeCodeList")
    val careTypes: List<CareTypeApiModel>,
    val coverImageIdList: List<Int>,
    val commission: Int,
    val latitude: Double?,
    val longitude: Double?,
)
package com.mommydndn.app.data.network.service.care.request


import com.mommydndn.app.data.network.service.care.model.CareTypeApiModel
import com.mommydndn.app.data.network.service.care.model.SalaryTypeApiModel
import com.mommydndn.app.data.network.service.common.model.LocationApiModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreateCareProviderRequest(
    @SerialName("emd")
    val workingNeighborhood: LocationApiModel,
    @SerialName("indOtherConditionIdList")
    val otherCondtions: List<Int>,
    @SerialName("introLine")
    val introduction: String,
    @SerialName("salaryTypeCode")
    val salaryType: SalaryTypeApiModel,
    @SerialName("caringTypeCodeList")
    val careTypes: List<CareTypeApiModel>,
    val salary: Int,
    val latitude: Double?,
    val longitude: Double?
)
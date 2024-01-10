package com.mommydndn.app.data.network.service.care.request


import com.mommydndn.app.data.network.service.care.model.CareTypeApiModel
import com.mommydndn.app.data.network.service.care.model.SalaryTypeApiModel
import com.mommydndn.app.data.network.service.location.model.LocationApiModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreateCareWorkerRequest(
    @SerialName("indOtherConditionIdList")
    val otherConditions: List<Int>,
    @SerialName("introLine")
    val introduction: String,
    @SerialName("salaryTypeCode")
    val salaryType: SalaryTypeApiModel,
    @SerialName("emd")
    val workingLocation: LocationApiModel,
    @SerialName("caringTypeCodeList")
    val careTypes: List<CareTypeApiModel>,
    val salary: Int,
    val latitude: Double?,
    val longitude: Double?
)
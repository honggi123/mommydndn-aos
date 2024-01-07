package com.mommydndn.app.data.network.model.care.request


import com.mommydndn.app.data.model.care.CaringType
import com.mommydndn.app.data.model.care.CaringTypeSerializer
import com.mommydndn.app.data.model.care.SalaryType
import com.mommydndn.app.data.model.care.SalaryTypeSerializer
import com.mommydndn.app.data.network.model.care.CareTypeApiModel
import com.mommydndn.app.data.network.model.care.SalaryTypeApiModel
import com.mommydndn.app.data.network.model.common.LocationApiModel
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
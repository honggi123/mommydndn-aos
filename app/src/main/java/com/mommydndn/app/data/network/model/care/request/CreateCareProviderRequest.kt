package com.mommydndn.app.data.network.model.care.request


import com.mommydndn.app.data.model.care.CaringType
import com.mommydndn.app.data.model.care.CaringTypeSerializer
import com.mommydndn.app.data.model.care.SalaryType
import com.mommydndn.app.data.model.care.SalaryTypeSerializer
import com.mommydndn.app.data.network.model.common.LocationApiModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreateCareProviderRequest(
    @SerialName("emd")
    val workingNeighborhood: LocationApiModel,
    @SerialName("indOtherConditionIdList")
    val individualOtherConditionIdList: List<Int>,
    @SerialName("introLine")
    val introduction: String,
    @Serializable(with = SalaryTypeSerializer::class)
    @SerialName("salaryTypeCode")
    val salaryType: SalaryType,
    @SerialName("caringTypeCodeList")
    val careTypes: List<@Serializable(with = CaringTypeSerializer::class) CaringType>,
    val salary: Int,
    val latitude: Double?,
    val longitude: Double?
)
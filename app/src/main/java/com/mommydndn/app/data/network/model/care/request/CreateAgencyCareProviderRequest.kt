package com.mommydndn.app.data.network.model.care.request

import com.mommydndn.app.data.model.care.CaringType
import com.mommydndn.app.data.model.care.CaringTypeSerializer
import com.mommydndn.app.data.model.location.EmdItem
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreateAgencyCareProviderRequest(
    @SerialName("caringTypeCodeList")
    val careTypeList: List<@Serializable(with = CaringTypeSerializer::class) CaringType>,
    @SerialName("emd")
    val emd: EmdItem,
    @SerialName("comOtherConditionIdList")
    val comOtherConditionIdList: List<Int>,
    @SerialName("introLine")
    val introduction: String,
    @SerialName("minMonthlySalary")
    val minMonthlySalary: Int,
    @SerialName("maxMonthlySalary")
    val maxMonthlySalary: Int,
    val coverImageIdList: List<Int>,
    val commission: Int,
    val latitude: Double?,
    val longitude: Double?,
)
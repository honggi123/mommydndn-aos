package com.mommydndn.app.data.network.model.request

import com.mommydndn.app.data.model.care.CaringType
import com.mommydndn.app.data.model.care.CaringTypeSerializer
import com.mommydndn.app.data.model.location.EmdItem
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CompanyCreationRequest(
    @SerialName("coverImageIdList")
    val coverImageIdList: List<Int>,
    @SerialName("caringTypeCodeList")
    val caringTypeCodeList: List<@Serializable(with = CaringTypeSerializer::class) CaringType>,
    @SerialName("emd")
    val emd: EmdItem,
    @SerialName("comOtherConditionIdList")
    val comOtherConditionIdList: List<Int>,
    @SerialName("introLine")
    val introLine: String,
    @SerialName("latitude")
    val latitude: Double?,
    @SerialName("longitude")
    val longitude: Double?,
    @SerialName("minMonthlySalary")
    val minMonthlySalary: Int,
    @SerialName("maxMonthlySalary")
    val maxMonthlySalary: Int,
    @SerialName("commission")
    val commission: Int,
)
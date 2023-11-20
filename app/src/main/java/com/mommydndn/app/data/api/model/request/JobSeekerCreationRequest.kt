package com.mommydndn.app.data.api.model.request


import com.mommydndn.app.data.api.model.response.Emd
import com.mommydndn.app.data.model.care.CaringType
import com.mommydndn.app.data.model.care.CaringTypeSerializer
import com.mommydndn.app.data.model.care.SalaryType
import com.mommydndn.app.data.model.care.SalaryTypeSerializer
import com.mommydndn.app.data.model.map.EmdItem
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class JobSeekerCreationRequest(
    @SerialName("caringTypeCodeList")
    val caringTypeCodeList: List<@Serializable(with = CaringTypeSerializer::class) CaringType>,
    @SerialName("emd")
    val emd: EmdItem,
    @SerialName("indOtherConditionIdList")
    val indOtherConditionIdList: List<Int>,
    @SerialName("introLine")
    val introLine: String,
    @SerialName("latitude")
    val latitude: Double,
    @SerialName("longitude")
    val longitude: Double,
    @SerialName("Double")
    val salary: Int,
    @Serializable(with = SalaryTypeSerializer::class)
    @SerialName("salaryTypeCode") val salaryTypeCode: SalaryType,
)
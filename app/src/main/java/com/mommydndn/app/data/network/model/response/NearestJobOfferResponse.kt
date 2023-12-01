package com.mommydndn.app.data.network.model.response


import com.mommydndn.app.data.model.care.CaringType
import com.mommydndn.app.data.model.care.CaringTypeSerializer
import com.mommydndn.app.data.model.care.SalaryType
import com.mommydndn.app.data.model.care.SalaryTypeSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NearestJobOfferResponse(
    @Serializable(with = CaringTypeSerializer::class)
    @SerialName("caringTypeCode")
    val caringTypeCode: CaringType,
    val jobOfferId: Int,
    val neighborhood: String,
    val salary: Int,
    @Serializable(with = SalaryTypeSerializer::class)
    val salaryTypeCode: SalaryType,
    val title: String
)
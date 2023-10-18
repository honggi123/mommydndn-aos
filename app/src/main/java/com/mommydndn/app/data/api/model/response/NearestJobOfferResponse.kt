package com.mommydndn.app.data.api.model.response


import com.mommydndn.app.data.model.care.CaringType
import com.mommydndn.app.data.model.care.SalaryType
import kotlinx.serialization.Serializable

@Serializable
data class NearestJobOfferResponse(
    val caringTypeCode: CaringType,
    val jobOfferId: Int,
    val neighborhood: String,
    val salary: Int,
    val salaryTypeCode: SalaryType,
    val title: String
)
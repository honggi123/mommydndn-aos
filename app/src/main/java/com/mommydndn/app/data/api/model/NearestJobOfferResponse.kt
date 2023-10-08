package com.mommydndn.app.data.api.model


import com.google.gson.annotations.SerializedName
import com.mommydndn.app.data.model.CaringType
import com.mommydndn.app.data.model.SalaryType
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
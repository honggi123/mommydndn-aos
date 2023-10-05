package com.mommydndn.app.data.api.model


import com.google.gson.annotations.SerializedName
import com.mommydndn.app.data.model.CaringType
import com.mommydndn.app.data.model.SalaryType

data class NearestJobOfferResponse(
    @SerializedName("caringTypeCode")
    val caringType: CaringType,
    @SerializedName("jobOfferId")
    val jobOfferId: Int,
    @SerializedName("neighborhood")
    val neighborhood: String,
    @SerializedName("salary")
    val salary: Int,
    @SerializedName("salaryTypeCode")
    val salaryType: SalaryType,
    @SerializedName("title")
    val title: String
)
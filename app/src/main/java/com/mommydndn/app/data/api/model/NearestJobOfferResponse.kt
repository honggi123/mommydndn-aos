package com.mommydndn.app.data.api.model


import com.google.gson.annotations.SerializedName

data class NearestJobOfferResponse(
    @SerializedName("caringTypeCode")
    val caringTypeCode: String,
    @SerializedName("jobOfferId")
    val jobOfferId: Int,
    @SerializedName("neighborhood")
    val neighborhood: String,
    @SerializedName("salary")
    val salary: Int,
    @SerializedName("salaryTypeCode")
    val salaryTypeCode: String,
    @SerializedName("title")
    val title: String
)
package com.mommydndn.app.data.network.model

import com.mommydndn.app.data.network.service.care.model.SalaryTypeApiModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NetworkNearestCareJob(
    @SerialName("jobOfferId")
    val id: Int,
    @SerialName("neighborhood")
    val neighborhoodName: String,
    @SerialName("salaryTypeCode")
    val salaryType: SalaryTypeApiModel,
    @SerialName("caringTypeCode")
    val careType: NetworkCareType,
    val salary: Int,
    val title: String
)
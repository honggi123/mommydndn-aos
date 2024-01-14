package com.mommydndn.app.data.network.service.location.response


import com.mommydndn.app.data.network.service.care.model.CareTypeApiModel
import com.mommydndn.app.data.network.service.care.model.SalaryTypeApiModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

typealias GetNearestCareJobListResponse = List<NearestCareJobApiModel>

@Serializable
data class NearestCareJobApiModel(
    @SerialName("jobOfferId")
    val id: Int,
    @SerialName("neighborhood")
    val neighborhoodName: String,
    @SerialName("salaryTypeCode")
    val salaryType: SalaryTypeApiModel,
    @SerialName("caringTypeCode")
    val careType: CareTypeApiModel,
    val salary: Int,
    val title: String
)




package com.mommydndn.app.data.network.feature.location.response


import com.mommydndn.app.data.model.care.CaringType
import com.mommydndn.app.data.model.care.CaringTypeSerializer
import com.mommydndn.app.data.model.care.SalaryType
import com.mommydndn.app.data.model.care.SalaryTypeSerializer
import com.mommydndn.app.domain.model.care.JobOffer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

typealias GetNearestJobOpeningListResponse = List<NearestJobOpeningApiModel>

@Serializable
data class NearestJobOpeningApiModel(
    @SerialName("jobOfferId")
    val id: Int,
    @SerialName("neighborhood")
    val neighborhoodName: String,
    @Serializable(with = SalaryTypeSerializer::class)
    val salaryTypeCode: SalaryType,
    @Serializable(with = CaringTypeSerializer::class)
    @SerialName("caringTypeCode")
    val careType: CaringType,
    val salary: Int,
    val title: String
)

fun GetNearestJobOpeningListResponse.toDomain(): List<JobOffer> {
    return this.map {
        JobOffer(
            title = it.title,
            neighborhood = it.neighborhoodName,
            salary = it.salary,
            salaryType = it.salaryTypeCode,
            caringType = it.careType
        )
    }
}


package com.mommydndn.app.data.network.model.response


import com.mommydndn.app.data.model.care.CaringType
import com.mommydndn.app.data.model.care.CaringTypeSerializer
import com.mommydndn.app.data.model.care.SalaryType
import com.mommydndn.app.data.model.care.SalaryTypeSerializer
import com.mommydndn.app.domain.model.care.JobOffer
import com.mommydndn.app.domain.model.care.JobSeeker
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

typealias GetNearestJobOffersResponse = List<GetNearestJobOfferResponse>

@Serializable
data class GetNearestJobOfferResponse(
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

fun GetNearestJobOffersResponse.toDomain(): List<JobOffer> {
    return this.map {
        JobOffer(
            title = it.title,
            neighborhood = it.neighborhood,
            salary = it.salary,
            salaryType = it.salaryTypeCode,
            caringType = it.caringTypeCode
        )
    }
}


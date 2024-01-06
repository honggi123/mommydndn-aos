package com.mommydndn.app.data.api.model.response

import com.mommydndn.app.data.model.care.CaringType
import com.mommydndn.app.data.model.care.CaringTypeSerializer
import com.mommydndn.app.domain.model.care.JobSeeker
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

typealias GetCareProviderListResponse = List<CareProviderResponse>

@Serializable
data class CareProviderResponse(
    @SerialName("nickname")
    val nickname: String,
    @SerialName("ageAndGender")
    val ageAndGender: String,
    @Serializable(with = CaringTypeSerializer::class)
    @SerialName("caringType")
    val caringType: CaringType,
    @SerialName("jobSeekerId")
    val jobSeekerId: Int,
    @SerialName("profileUrl")
    val profileUrl: String
)

fun GetCareProviderListResponse.toDomain(): List<JobSeeker> {
    return this.map {
        JobSeeker(
            nickname = it.nickname,
            ageAndGender = it.ageAndGender,
            caringType = it.caringType,
            jobSeekerId = it.jobSeekerId,
            profileUrl = it.profileUrl,
        )
    }
}

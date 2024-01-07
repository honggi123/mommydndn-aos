//package com.mommydndn.app.data.api.model.response
//
//import com.mommydndn.app.data.model.care.CaringType
//import com.mommydndn.app.data.model.care.CaringTypeSerializer
//import com.mommydndn.app.domain.model.care.JobSeeker
//import kotlinx.serialization.SerialName
//import kotlinx.serialization.Serializable
//
//typealias GetCareProviderListResponse = List<CareProviderResponse>
//
//@Serializable
//data class CareProviderResponse(
//    @SerialName("jobSeekerId")
//    val id: Int,
//    @SerialName("profileUrl")
//    val profileUrl: String,
//    @Serializable(with = CaringTypeSerializer::class)
//    @SerialName("caringType")
//    val careType: CaringType,
//    val nickname: String,
//    val ageAndGender: String
//)
//
//fun GetCareProviderListResponse.toDomain(): List<JobSeeker> {
//    return this.map {
//        JobSeeker(
//            nickname = it.nickname,
//            ageAndGender = it.ageAndGender,
//            caringType = it.careType,
//            jobSeekerId = it.id,
//            profileUrl = it.profileUrl,
//        )
//    }
//}

package com.mommydndn.app.data.model.care

import com.mommydndn.app.data.model.care.CaringType
import com.mommydndn.app.data.model.user.UserTypeSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class JobSeeker(
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


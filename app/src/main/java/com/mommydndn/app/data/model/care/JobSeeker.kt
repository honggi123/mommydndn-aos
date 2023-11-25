package com.mommydndn.app.data.model.care

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


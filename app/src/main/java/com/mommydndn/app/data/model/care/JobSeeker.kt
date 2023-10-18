package com.mommydndn.app.data.model.care

import com.mommydndn.app.data.model.care.CaringType
import kotlinx.serialization.Serializable

@Serializable
data class JobSeeker(
    val nickname: String,
    val ageAndGender: String,
    val caringType: CaringType,
    val jobSeekerId: Int,
    val profileUrl: String
)

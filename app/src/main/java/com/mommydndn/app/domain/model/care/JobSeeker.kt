package com.mommydndn.app.domain.model.care

import com.mommydndn.app.data.model.care.CaringType
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

data class JobSeeker(
    val nickname: String,
    val ageAndGender: String,
    val caringType: CaringType,
    val jobSeekerId: Int,
    val profileUrl: String
)



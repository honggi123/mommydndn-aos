package com.mommydndn.app.data.model

import kotlinx.serialization.Serializable

@Serializable
data class JobSeeker(
    val nickname: String,
    val ageAndGender: String,
    val caringType: CaringType,
    val jobSeekerId: Int,
    val profileUrl: String
)

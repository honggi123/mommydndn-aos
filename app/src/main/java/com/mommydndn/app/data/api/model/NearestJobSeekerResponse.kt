package com.mommydndn.app.data.api.model


import com.google.gson.annotations.SerializedName
import com.mommydndn.app.data.model.CaringType
import kotlinx.serialization.Serializable

@Serializable
data class NearestJobSeekerResponse(
    val nickname: String,
    val ageAndGender: String,
    val caringType: CaringType,
    val jobSeekerId: Int,
    val profileUrl: String
)
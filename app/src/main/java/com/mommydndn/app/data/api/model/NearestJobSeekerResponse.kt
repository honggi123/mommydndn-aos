package com.mommydndn.app.data.api.model


import com.google.gson.annotations.SerializedName
import com.mommydndn.app.data.model.CaringType

data class NearestJobSeekerResponse(
    @SerializedName("nickname")
    val nickname: String,
    @SerializedName("ageAndGender")
    val ageAndGender: String,
    @SerializedName("caringType")
    val caringType: CaringType,
    @SerializedName("jobSeekerId")
    val jobSeekerId: Int,
    @SerializedName("profileUrl")
    val profileUrl: String
)
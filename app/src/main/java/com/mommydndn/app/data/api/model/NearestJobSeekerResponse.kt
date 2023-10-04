package com.mommydndn.app.data.api.model


import com.google.gson.annotations.SerializedName

data class NearestJobSeekerResponse(
    @SerializedName("ageAndGender")
    val ageAndGender: String,
    @SerializedName("caringType")
    val caringType: String,
    @SerializedName("jobSeekerId")
    val jobSeekerId: Int,
    @SerializedName("profileUrl")
    val profileUrl: String
)
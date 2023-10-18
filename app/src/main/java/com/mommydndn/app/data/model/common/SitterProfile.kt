package com.mommydndn.app.data.model.common

import com.mommydndn.app.data.model.care.CaringType

data class SitterProfile(
    val profileImgUrl: String,
    val name: String,
    val ageAndGender: String,
    val caringType: CaringType
)

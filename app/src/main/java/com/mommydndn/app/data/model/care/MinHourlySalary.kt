package com.mommydndn.app.data.model.care


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MinHourlySalary(
    @SerialName("minHourlySalary")
    val minHourlySalary: Int
)
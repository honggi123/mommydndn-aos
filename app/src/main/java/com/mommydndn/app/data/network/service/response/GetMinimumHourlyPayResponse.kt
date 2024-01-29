package com.mommydndn.app.data.network.service.response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetMinimumHourlyPayResponse(
    @SerialName("minHourlySalary")
    val pay: Int
)
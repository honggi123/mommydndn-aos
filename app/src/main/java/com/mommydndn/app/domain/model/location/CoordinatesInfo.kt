package com.mommydndn.app.domain.model.location

data class CoordinatesInfo(
    val latitude: Double,
    val longitude: Double,
    val address: String = ""
)

package com.mommydndn.app.data.network.model.care

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class SortingTypeApiModel {
    @SerialName("HOURLY")
    LATEST,

    @SerialName("MOST_VIEW")
    MOST_VIEW,

    @SerialName("HIGHEST_SALARY")
    HIGHEST_SALARY,

    @SerialName("CLOSEST")
    CLOSEST
}
package com.mommydndn.app.data.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class NetworkWorkPeriod {
    @SerialName("ONETIME") ONE_TIME, REGULAR
}
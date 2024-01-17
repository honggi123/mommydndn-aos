package com.mommydndn.app.data.network.model

import kotlinx.serialization.Serializable

@Serializable
enum class NetworkPayPeriod {
    HOURLY,
    DAILY,
    WEEKLY,
    MONTHLY,
    NEGOTIATION
}
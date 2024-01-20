package com.mommydndn.app.domain.model

data class Pay(
    val period: PayPeriod,
    val pay: Int,
)

enum class PayPeriod {
    Hourly,
    Daily,
    Weekly,
    Monthly,
    Negotiable,
}
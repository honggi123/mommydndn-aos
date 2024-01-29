package com.mommydndn.app.domain.model

data class Pay(
    val period: PayPeriod,
    val amount: Int,
)

enum class PayPeriod {
    Hourly,
    Daily,
    Weekly,
    Monthly,
    Negotiable,
}
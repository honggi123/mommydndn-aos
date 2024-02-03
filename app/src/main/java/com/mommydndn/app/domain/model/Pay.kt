package com.mommydndn.app.domain.model

data class Pay(
    val period: PayPeriod,
    val amount: Int,
) {
    constructor() : this(
        period = PayPeriod.Negotiable,
        amount = 0
    )
}

enum class PayPeriod {
    Hourly,
    Daily,
    Weekly,
    Monthly,
    Negotiable,
}
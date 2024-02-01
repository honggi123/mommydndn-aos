package com.mommydndn.app.data.mapper

import com.mommydndn.app.data.network.model.NetworkPayPeriod
import com.mommydndn.app.domain.model.PayPeriod

internal fun NetworkPayPeriod.transformToDomainPayPeriod(): PayPeriod = when (this) {
    NetworkPayPeriod.HOURLY -> PayPeriod.Hourly
    NetworkPayPeriod.DAILY -> PayPeriod.Daily
    NetworkPayPeriod.WEEKLY -> PayPeriod.Weekly
    NetworkPayPeriod.MONTHLY -> PayPeriod.Monthly
    NetworkPayPeriod.NEGOTIATION -> PayPeriod.Negotiable
}
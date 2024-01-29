package com.mommydndn.app.ui.care.list.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.mommydndn.app.R
import com.mommydndn.app.domain.model.CareType
import com.mommydndn.app.domain.model.PayPeriod
import com.mommydndn.app.domain.model.WorkPeriod
import java.time.Duration
import java.time.ZoneId
import java.time.ZonedDateTime

val CareType.displayName: String
    @Composable
    get() = when (this) {
        CareType.ChildCare -> stringResource(R.string.child_care)
        CareType.SeniorCare -> stringResource(R.string.senior_care)
        CareType.SchoolTransportation -> stringResource(R.string.school_transportation)
        CareType.Housekeeping -> stringResource(R.string.housekeeping)
    }


internal val PayPeriod.displayName: String
    @Composable
    get() = when (this) {
        PayPeriod.Hourly -> stringResource(R.string.hourly_pay)
        PayPeriod.Daily -> stringResource(R.string.daily_pay)
        PayPeriod.Weekly -> stringResource(R.string.weekly_pay)
        PayPeriod.Monthly -> stringResource(R.string.monthly_pay)
        PayPeriod.Negotiable -> stringResource(R.string.negotiation)
    }

@Composable
internal fun WorkPeriod.displayName(): String = when (this) {
    WorkPeriod.ShortTerm -> stringResource(R.string.one_time)
    WorkPeriod.Regular -> stringResource(R.string.regular)
}

@Composable
fun ZonedDateTime.asTimeAgo(): String {
    val now = ZonedDateTime.now(ZoneId.of("Asia/Seoul"))

    val duration = Duration.between(this, now)

    val days = duration.toDays()

    val hours = duration.toHours()

    return if (days > 0) {
        stringResource(R.string.days_ago, days)
    } else if (hours > 0) {
        stringResource(R.string.hours_ago, hours)
    } else {
        stringResource(R.string.minutes_ago, duration.toMinutes())
    }
}
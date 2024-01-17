package com.mommydndn.app.ui.care

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.mommydndn.app.R
import com.mommydndn.app.domain.model.CareType
import com.mommydndn.app.domain.model.PayPeriod
import com.mommydndn.app.domain.model.WorkPeriod

@Composable
internal fun CareType.displayName(): String {
    return when (this) {
        CareType.ChildCare -> stringResource(R.string.child_care)
        CareType.SeniorCare -> stringResource(R.string.senior_care)
        CareType.SchoolTransportation -> stringResource(R.string.school_transportation)
        CareType.Housekeeping -> stringResource(R.string.housekeeping)
    }
}

@Composable
internal fun PayPeriod.displayName(): String = when (this) {
    PayPeriod.Hourly -> stringResource(R.string.hourly_pay)
    PayPeriod.Daily -> stringResource(R.string.daily_pay)
    PayPeriod.Weekly -> stringResource(R.string.weekly_pay)
    PayPeriod.Monthly -> stringResource(R.string.monthly_pay)
    PayPeriod.Negotiable -> stringResource(R.string.negotiation)
}

@Composable
internal fun WorkPeriod.displayName(): String = when (this) {
    WorkPeriod.OneTime -> stringResource(R.string.one_time)
    WorkPeriod.Regular -> stringResource(R.string.regular)
}
package com.mommydndn.app.ui.features.care

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.mommydndn.app.R
import com.mommydndn.app.domain.model.care.CareType
import com.mommydndn.app.domain.model.care.PayPeriod
import com.mommydndn.app.domain.model.care.WorkPeriod

@Composable
internal fun CareType.displayName(): String {
    return when (this) {
        CareType.CHILD_CARE -> stringResource(R.string.child_care)
        CareType.SENIOR_CARE -> stringResource(R.string.senior_care)
        CareType.SCHOOL_TRANSPORTATION -> stringResource(R.string.school_transportation)
        CareType.HOUSEKEEPING -> stringResource(R.string.housekeeping)
    }
}

@Composable
internal fun PayPeriod.displayName(): String = when (this) {
    PayPeriod.HOURLY -> stringResource(R.string.hourly_pay)
    PayPeriod.DAILY -> stringResource(R.string.daily_pay)
    PayPeriod.WEEKLY -> stringResource(R.string.weekly_pay)
    PayPeriod.MONTHLY -> stringResource(R.string.monthly_pay)
    PayPeriod.NEGOTIATION -> stringResource(R.string.negotiation)
}

@Composable
internal fun WorkPeriod.displayName(): String = when (this) {
    WorkPeriod.ONE_TIME -> stringResource(R.string.one_time)
    WorkPeriod.REGULAR -> stringResource(R.string.regular)
}
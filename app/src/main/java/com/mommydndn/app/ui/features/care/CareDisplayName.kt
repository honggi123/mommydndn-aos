package com.mommydndn.app.ui.features.care

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.mommydndn.app.R
import com.mommydndn.app.domain.model.care.CareType
import com.mommydndn.app.domain.model.care.NearbyNeighborhoodDistance
import com.mommydndn.app.domain.model.care.PayPeriod
import com.mommydndn.app.domain.model.care.WorkPeriod
import java.text.DecimalFormat
import java.time.format.TextStyle
import java.util.Locale

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

@Composable
internal fun CareFilter.displayName(): String = when (this) {
    is NeighborhoodsFilter -> displayName()
    is CareTypesFilter -> displayName()
    is DaysOfWeekFilter -> displayName()
    is PayFilter -> displayName()
    is WorkHoursFilter -> displayName()
    is WorkPeriodFilter -> displayName()
}

@Composable
internal fun NeighborhoodsFilter.displayName(): String {
    val nearbyNeighborhoodsCount = when (nearbyNeighborhoodDistance) {
        NearbyNeighborhoodDistance.IMMEDIATE -> 0
        NearbyNeighborhoodDistance.NEARBY -> neighborhood.nearbyNeighborhoods.size
        NearbyNeighborhoodDistance.DISTANT -> neighborhood.distantNeighborhoods.size
        NearbyNeighborhoodDistance.VERY_DISTANT -> neighborhood.veryDistantNeighborhoods.size
    }

    val postfix = if (nearbyNeighborhoodsCount == 0) {
        ""
    } else {
        stringResource(R.string.other_size, nearbyNeighborhoodsCount)
    }

    return neighborhood.name + postfix
}

@Composable
internal fun CareTypesFilter.displayName(): String {
    return if (careTypes.isNullOrEmpty()) {
        stringResource(R.string.care_type)
    } else {
        careTypes.sorted().let { careTypes ->
            val postfix = if (careTypes.size > 2) {
                stringResource(id = R.string.other_size, careTypes.size - 2)
            } else {
                ""
            }

            @Suppress("SimplifiableCallChain")
            careTypes.take(2)
                .map { it.displayName() }
                .joinToString(postfix = postfix)
        }
    }
}

internal val payDecimalFormat = DecimalFormat("#.#")

// todo: 최소 금액만 설정될 수 있는지? 가능해야 할 것 같은데 최소 금액만 설정할 수 있다면 표시 방식은?
@Composable
internal fun PayFilter.displayName(): String {
    return if (minimum != null && maximum != null) {
        with(payDecimalFormat) {
            stringResource(
                R.string.hourly_pay_between,
                format(minimum / 10_000.0),
                format(maximum / 10_000.0),
            )
        }
    } else {
        stringResource(R.string.pay)
    }
}

@Composable
internal fun DaysOfWeekFilter.displayName(): String {
    return if (!daysOfWeek.isNullOrEmpty()) {
        daysOfWeek.sorted().let { daysOfWeek ->
            val postfix: String = if (daysOfWeek.size >= 4) {
                stringResource(id = R.string.other_size, daysOfWeek.size - 3)
            } else {
                ""
            }

            daysOfWeek.take(3).joinToString(
                separator = "",
                postfix = postfix,
                transform = {
                    it.getDisplayName(TextStyle.NARROW_STANDALONE, Locale.KOREAN)
                }
            )
        }
    } else {
        stringResource(R.string.day_of_week)
    }
}

@Composable
internal fun WorkHoursFilter.displayName(): String {
    return if (startTime != null && endTime != null) {
        "${startTime.hour}시-${endTime.hour}시"
    } else {
        stringResource(R.string.time)
    }
}

@Composable
internal fun WorkPeriodFilter.displayName(): String {
    return when (workPeriod) {
        WorkPeriod.ONE_TIME -> stringResource(R.string.one_time)
        WorkPeriod.REGULAR -> stringResource(R.string.regular)
        else -> stringResource(R.string.one_time_slash_regular)
    }
}
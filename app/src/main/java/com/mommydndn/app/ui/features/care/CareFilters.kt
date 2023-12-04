package com.mommydndn.app.ui.features.care

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.mommydndn.app.R
import com.mommydndn.app.domain.model.care.CareType
import com.mommydndn.app.domain.model.care.NearbyNeighborhoodDistance
import com.mommydndn.app.domain.model.care.WorkPeriod
import com.mommydndn.app.domain.model.user.Neighborhood
import java.time.DayOfWeek
import java.time.LocalTime

// todo: rename?
enum class CareOrderBy {
    LATEST, VIEWS, HIGHEST_PAY, NEAREST,
}

@Composable
internal fun CareOrderBy.displayName() = when (this) {
    CareOrderBy.LATEST -> stringResource(R.string.order_by_latest)
    CareOrderBy.VIEWS -> stringResource(R.string.order_by_views)
    CareOrderBy.HIGHEST_PAY -> stringResource(R.string.order_by_highest_pay)
    CareOrderBy.NEAREST -> stringResource(R.string.order_by_nearest)
}

sealed interface CareFilter

data class NeighborhoodsFilter(
    val neighborhood: Neighborhood = Neighborhood(
        name = "",
        latitude = 0.0,
        longitude = 0.0,
        nearbyNeighborhoods = emptyList(),
        distantNeighborhoods = emptyList(),
        veryDistantNeighborhoods = emptyList()
    ),
    val nearbyNeighborhoodDistance: NearbyNeighborhoodDistance = NearbyNeighborhoodDistance.VERY_DISTANT,
) : CareFilter

data class CareTypesFilter(
    val careTypes: List<CareType>? = null
) : CareFilter

data class PayFilter(
    val minimum: Int? = null,
    val maximum: Int? = null,
) : CareFilter

data class DaysOfWeekFilter(
    val daysOfWeek: List<DayOfWeek>? = null
) : CareFilter

data class WorkHoursFilter(
    val startTime: LocalTime? = null,
    val endTime: LocalTime? = null,
) : CareFilter

data class WorkPeriodFilter(
    val workPeriod: WorkPeriod? = null
) : CareFilter

internal val CareFilter.hasValue: Boolean get() = when (this) {
    is NeighborhoodsFilter -> true // always has value
    is CareTypesFilter -> !careTypes.isNullOrEmpty()
    is PayFilter -> minimum != null || maximum != null
    is DaysOfWeekFilter -> !daysOfWeek.isNullOrEmpty()
    is WorkHoursFilter -> startTime != null && endTime != null
    is WorkPeriodFilter -> workPeriod != null
}
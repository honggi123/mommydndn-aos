package com.mommydndn.app.ui.care.filters

import androidx.compose.runtime.Composable

// TODO: 정돈해야 됨,
sealed interface CareFilter<T> {

    val selected: Boolean

    @Composable
    fun displayName(): String

    fun predicate(value: T): Boolean
}

data class CareFilters(
    val neighborhoodFilter: NeighborhoodFilter = NeighborhoodFilter(),
    val careTypesFilter: CareTypesFilter = CareTypesFilter(),
    val daysOfWeekFilter: DaysOfWeekFilter = DaysOfWeekFilter(),
    val workHoursFilter: WorkHoursFilter = WorkHoursFilter(),
    val workPeriodFilter: WorkPeriodFilter = WorkPeriodFilter(),
)
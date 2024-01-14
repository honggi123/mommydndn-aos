package com.mommydndn.app.ui.care.filter

import androidx.compose.runtime.Composable

// TODO: 리팩토링 필수
sealed interface CareFilters<T> {

    val selected: Boolean

    @Composable
    fun displayName(): String

    fun predicate(value: T): Boolean
}

//data class CareFilters(
//    val neighborhoodFilter: NeighborhoodFilter = NeighborhoodFilter(),
//    val careTypesFilter: CareTypesFilter = CareTypesFilter(),
//    val daysOfWeekFilter: DaysOfWeekFilter = DaysOfWeekFilter(),
//    val workHoursFilter: WorkHoursFilter = WorkHoursFilter(),
//    val workPeriodFilter: WorkPeriodFilter = WorkPeriodFilter(),
//)
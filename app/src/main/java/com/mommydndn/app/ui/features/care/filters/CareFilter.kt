package com.mommydndn.app.ui.features.care.filters

import androidx.compose.runtime.Composable

sealed interface CareFilter<T> {

    val hasValue: Boolean

    @Composable
    fun displayName(): String

    fun predicate(value: T): Boolean
}

data class CareFilters(
    val neighborhoodsFilter: NeighborhoodsFilter = NeighborhoodsFilter(),
    val careTypesFilter: CareTypesFilter = CareTypesFilter(),
    val daysOfWeekFilter: DaysOfWeekFilter = DaysOfWeekFilter(),
    val workHoursFilter: WorkHoursFilter = WorkHoursFilter(),
    val workPeriodFilter: WorkPeriodFilter = WorkPeriodFilter(),
)

fun CareFilters.toMap(): Map<Class<out CareFilter<*>>, CareFilter<*>> = buildMap {
    put(neighborhoodsFilter.javaClass, neighborhoodsFilter)
    put(careTypesFilter.javaClass, careTypesFilter)
    put(daysOfWeekFilter.javaClass, daysOfWeekFilter)
    put(workHoursFilter.javaClass, workHoursFilter)
    put(workPeriodFilter.javaClass, workPeriodFilter)
}
package com.mommydndn.app.data.model.care.Filter

import com.mommydndn.app.data.model.care.CaringType
import com.mommydndn.app.data.model.care.CaringTypeItem
import com.mommydndn.app.data.model.care.DistanceType
import com.mommydndn.app.data.model.care.DistanceTypeItem
import com.mommydndn.app.data.model.care.SortingType
import com.mommydndn.app.data.model.care.SortingTypeItem
import com.mommydndn.app.data.model.care.WorkPeriodType
import com.mommydndn.app.data.model.care.WorkPeriodTypeItem
import com.mommydndn.app.data.model.common.DayOfWeekItem
import com.mommydndn.app.data.model.common.DayOfWeekType
import com.mommydndn.app.data.model.map.EmdItem
import java.time.LocalTime

sealed class FilterItemsType() {
    data class Sorting(
        val list: List<SortingTypeItem> = listOf(
            SortingTypeItem(SortingType.LATEST, true),
            SortingTypeItem(SortingType.MOST_VIEW),
            SortingTypeItem(SortingType.HIGHEST_SALARY),
            SortingTypeItem(SortingType.CLOSEST)
        )
    ) : FilterItemsType()

    data class Caring(
        val isAllChecked: Boolean = false,
        var list: List<CaringTypeItem> = listOf(
            CaringTypeItem(CaringType.PARENTING, CaringType.PARENTING.value, true),
            CaringTypeItem(CaringType.HOUSEKEEPING, CaringType.HOUSEKEEPING.value, true),
            CaringTypeItem(CaringType.NURSING, CaringType.NURSING.value, true),
            CaringTypeItem(CaringType.SCHOOL, CaringType.SCHOOL.value, true)
        )
    ) : FilterItemsType()

    data class NeighborhoodScope(
        val myLocationName: String,
        val list: List<DistanceTypeItem> = listOf(
            DistanceTypeItem(DistanceType.CLOSEST),
            DistanceTypeItem(DistanceType.NEAR),
            DistanceTypeItem(DistanceType.FAR),
            DistanceTypeItem(DistanceType.FURTHEST, true)
        )
    ) : FilterItemsType()

    data class Period(
        var list: List<WorkPeriodTypeItem> = listOf(
            WorkPeriodTypeItem(WorkPeriodType.REGULAR, true),
            WorkPeriodTypeItem(WorkPeriodType.ONETIME)
        )
    ) : FilterItemsType()

    data class Time(
        val startTime: LocalTime? = null,
        val endTime: LocalTime? = null
    ) : FilterItemsType()

    data class Day(
        var list: List<DayOfWeekItem> = listOf(
            DayOfWeekItem(DayOfWeekType.MON, isSelected = true),
            DayOfWeekItem(DayOfWeekType.TUE, isSelected = true),
            DayOfWeekItem(DayOfWeekType.WED, isSelected = true),
            DayOfWeekItem(DayOfWeekType.THU, isSelected = true),
            DayOfWeekItem(DayOfWeekType.FRI, isSelected = true),
            DayOfWeekItem(DayOfWeekType.SAT, isSelected = true),
            DayOfWeekItem(DayOfWeekType.SUN, isSelected = true)
        )
    ) : FilterItemsType()

}
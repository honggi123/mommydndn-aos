package com.mommydndn.app.data.model.care.Filter

import com.mommydndn.app.data.model.care.CaringType
import com.mommydndn.app.data.model.care.CaringTypeItem
import com.mommydndn.app.data.model.care.SortingType
import com.mommydndn.app.data.model.care.SortingTypeItem
import com.mommydndn.app.data.model.care.WorkPeriodType
import com.mommydndn.app.data.model.care.WorkPeriodTypeItem
import com.mommydndn.app.data.model.common.DayOfWeekItem
import com.mommydndn.app.data.model.common.DayOfWeekType
import java.time.LocalTime

sealed class FilterItemsType() {
    data class Sorting(
        val list: List<SortingTypeItem>
    ) : FilterItemsType()

    data class Caring(
        val isAllChecked: Boolean = false,
        var list: List<CaringTypeItem>
    ) : FilterItemsType()

    data class NeighborhoodScope(
        val list: List<Int>
    ) : FilterItemsType()

    data class Period(
        val list: List<WorkPeriodTypeItem>
    ) : FilterItemsType()

    data class Time(
        val startTime: LocalTime?,
        val endTime: LocalTime?
    ) : FilterItemsType()

    data class Day(
        var list: List<DayOfWeekItem>
    ) : FilterItemsType()

}
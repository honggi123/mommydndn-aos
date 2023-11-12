package com.mommydndn.app.data.model.care.Filter

import com.mommydndn.app.data.model.care.CaringType
import com.mommydndn.app.data.model.care.SortingType
import com.mommydndn.app.data.model.care.WorkPeriodType
import java.time.LocalTime

sealed class FilterItemsType() {
    data class Sorting(
        val list: List<SortingType>
    ) : FilterItemsType()

    data class Caring(
        val list: List<CaringType>
    ) : FilterItemsType()

    data class NeighborhoodScope(
        val list: List<String>
    ) : FilterItemsType()

    data class Period(
        val list: List<WorkPeriodType>
    ) : FilterItemsType()

    data class Time(
        val startTime: LocalTime,
        val endTime: LocalTime
    ) : FilterItemsType()

}
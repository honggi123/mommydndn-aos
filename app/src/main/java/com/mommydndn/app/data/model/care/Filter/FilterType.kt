package com.mommydndn.app.data.model.care.Filter

import com.mommydndn.app.utils.DateTimeUtils
import com.mommydndn.app.utils.StringUtils

sealed class FilterType(
    open val items: FilterItemsType,
    open val isSelected: Boolean = false
) {
    abstract val displayingName: String

    data class Sorting(
        override var items: FilterItemsType.Sorting,
        override var isSelected: Boolean
    ) : FilterType(items, isSelected) {


        override val displayingName: String
            get() {
                val selectedSorting = items.list.filter { it.isSelected }.map { it.sortingType }
                return if (selectedSorting.isNotEmpty()) {
                    selectedSorting.first().diaplayingName
                } else {
                    "최신순"
                }
            }
    }

    data class Caring(
        override var items: FilterItemsType.Caring,
        override var isSelected: Boolean
    ) : FilterType(items, isSelected) {
        override val displayingName: String
            get() {
                val selectedCaring = items.list.filter { it.isSelected }.map { it.caringType }
                return if (selectedCaring.isNotEmpty()) {
                    StringUtils.getConcatenatedCommasString(selectedCaring.map { it.value })
                } else {
                    "돌봄종류"
                }
            }
    }

    data class NeighborhoodScope(
        override val items: FilterItemsType.NeighborhoodScope,
        override val displayingName: String,
        override val isSelected: Boolean
    ) : FilterType(items, isSelected)

    data class Period(
        override val items: FilterItemsType.Period,
        override val isSelected: Boolean
    ) : FilterType(items, isSelected) {
        override val displayingName: String
            get() {
                val selectedPeriod = items.list.filter { it.isSelected }.map { it.workPeriodType }
                return if (selectedPeriod.isNotEmpty()) {
                    selectedPeriod.first().value
                } else {
                    "정기"
                }
            }
    }

    data class Time(
        override val items: FilterItemsType.Time,
        override val isSelected: Boolean
    ) : FilterType(items, isSelected) {
        override val displayingName: String
            get() {
                val startTime = items.startTime
                val endTime = items.endTime

                return if (startTime != null && endTime != null) {
                    DateTimeUtils.getDutarionHourString(startTime, endTime) ?: "시간"
                } else {
                    "시간"
                }
            }
    }

    data class Day(
        override var items: FilterItemsType.Day,
        override var isSelected: Boolean
    ) : FilterType(items, isSelected) {
        override val displayingName: String
            get() {
                val selectedDays = items.list.filter { it.isSelected }.map { it.type }
                return if (selectedDays.isNotEmpty()) {
                    StringUtils.getConcatenatedString(selectedDays.map { it.displayingName })
                } else {
                    "요일"
                }
            }
    }
}


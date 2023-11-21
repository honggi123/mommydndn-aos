package com.mommydndn.app.data.model.care.Filter

import com.mommydndn.app.data.model.care.DistanceType
import com.mommydndn.app.utils.DateTimeUtils
import com.mommydndn.app.utils.StringUtils

sealed class FilterType(
    open val items: FilterItemsType,
) {
    abstract val displayingName: String
    abstract val isSelected: Boolean

    data class Sorting(
        override var items: FilterItemsType.Sorting,
    ) : FilterType(items) {
        override val displayingName: String
            get() {
                val selectedSorting = items.list.filter { it.isSelected }.map { it.sortingType }
                return if (selectedSorting.isNotEmpty()) {
                    selectedSorting.first().diaplayingName
                } else {
                    "최신순"
                }
            }
        override val isSelected: Boolean
            get() {
                val selectedSorting = items.list.filter { it.isSelected }.map { it.sortingType }
                return if (selectedSorting.isNotEmpty()) {
                    true
                } else {
                    false
                }
            }
    }

    data class Caring(
        override var items: FilterItemsType.Caring,
    ) : FilterType(items) {
        override val displayingName: String
            get() {
                val selectedCaring = items.list.filter { it.isSelected }.map { it.caringType }
                return if (selectedCaring.isNotEmpty()) {
                    StringUtils.getConcatenatedCommasString(selectedCaring.map { it.value })
                } else {
                    "돌봄종류"
                }
            }

        override val isSelected: Boolean
            get() {
                return if (items.list.all { it.isSelected }) {
                    false
                } else {
                    true
                }
            }
    }

    data class NeighborhoodScope(
        override val items: FilterItemsType.NeighborhoodScope,
        override val displayingName: String,
    ) : FilterType(items) {
        override val isSelected: Boolean
            get() {
                val selectedDistance = items.list.filter { it.isSelected }.first().distantceType
                return if (selectedDistance == DistanceType.FURTHEST) {
                    false
                } else {
                    true
                }
            }
    }

    data class Period(
        override val items: FilterItemsType.Period,
    ) : FilterType(items) {
        override val displayingName: String
            get() {
                val selectedPeriod = items.list.filter { it.isSelected }.map { it.workPeriodType }
                return if (selectedPeriod.isNotEmpty()) {
                    selectedPeriod.first().value
                } else {
                    "정기"
                }
            }

        override val isSelected: Boolean
            get() {
                val selectedPeriod = items.list.filter { it.isSelected }.map { it.workPeriodType }
                return if (selectedPeriod.isNotEmpty()) {
                    true
                } else {
                    false
                }
            }
    }

    data class Time(
        override val items: FilterItemsType.Time,
    ) : FilterType(items) {
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

        override val isSelected: Boolean
            get() {
                val startTime = items.startTime
                val endTime = items.endTime

                return if (startTime != null || endTime != null) {
                    false
                } else {
                    true
                }
            }
    }

    data class Day(
        override var items: FilterItemsType.Day,
    ) : FilterType(items) {
        override val displayingName: String
            get() {
                val selectedDays = items.list.filter { it.isSelected }.map { it.type }
                return if (selectedDays.isNotEmpty()) {
                    StringUtils.getConcatenatedString(selectedDays.map { it.displayingName })
                } else {
                    "요일"
                }
            }

        override val isSelected: Boolean
            get() {
                return if (items.list.all { it.isSelected }) {
                    false
                } else {
                    true
                }
            }
    }
}


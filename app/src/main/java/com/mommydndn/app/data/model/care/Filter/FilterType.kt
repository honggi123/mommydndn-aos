package com.mommydndn.app.data.model.care.Filter

sealed class FilterType(
    open val displayingName: String,
    open val items: FilterItemsType,
    open val isSelected: Boolean = false
) {
    data class Sorting(
        override var items: FilterItemsType.Sorting,
        override var displayingName: String,
        override var isSelected: Boolean
    ) : FilterType(displayingName, items, isSelected)

    data class Caring(
        override var items: FilterItemsType.Caring,
        override val displayingName: String,
        override var isSelected: Boolean
    ) : FilterType(displayingName, items, isSelected)

    data class NeighborhoodScope(
        override val items: FilterItemsType.NeighborhoodScope,
        override val displayingName: String,
        override val isSelected: Boolean
    ) : FilterType(displayingName, items, isSelected)

    data class Period(
        override val items: FilterItemsType.Period,
        override val displayingName: String,
        override val isSelected: Boolean
    ) : FilterType(displayingName, items, isSelected)

    data class Time(
        override val items: FilterItemsType.Time,
        override val displayingName: String,
        override val isSelected: Boolean
    ) : FilterType(displayingName, items, isSelected)

    data class Day(
        override var items: FilterItemsType.Day,
        override val displayingName: String,
        override var isSelected: Boolean
    ) : FilterType(displayingName, items, isSelected)
}


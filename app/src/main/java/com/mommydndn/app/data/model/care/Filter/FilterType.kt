package com.mommydndn.app.data.model.care.Filter

sealed class FilterType(
    open val displayingName: String,
    open val itemsType: FilterItemsType,
    open val isSelected: Boolean = false
) {
    data class Sorting(
        override val itemsType: FilterItemsType.Sorting,
        override val displayingName: String,
        override val isSelected: Boolean
    ) : FilterType(displayingName, itemsType, isSelected)

    data class Caring(
        override val itemsType: FilterItemsType.Caring,
        override val displayingName: String,
        override val isSelected: Boolean
    ) : FilterType(displayingName, itemsType, isSelected)

    data class NeighborhoodScope(
        override val itemsType: FilterItemsType.NeighborhoodScope,
        override val displayingName: String,
        override val isSelected: Boolean
    ) : FilterType(displayingName, itemsType, isSelected)

    data class Period(
        override val itemsType: FilterItemsType.Period,
        override val displayingName: String,
        override val isSelected: Boolean
    ) : FilterType(displayingName, itemsType, isSelected)

    data class Time(
        override val itemsType: FilterItemsType.Time,
        override val displayingName: String,
        override val isSelected: Boolean
    ) : FilterType(displayingName, itemsType, isSelected)
}


package com.mommydndn.app.ui.features.care

import com.mommydndn.app.domain.model.care.CareType
import com.mommydndn.app.domain.model.care.NearbyNeighborhoodDistance
import com.mommydndn.app.domain.model.care.WorkPeriod
import com.mommydndn.app.domain.model.user.Neighborhood
import com.mommydndn.app.ui.features.care.CareFilter.State
import java.time.DayOfWeek
import java.time.LocalTime

enum class CareOrderBy {
    LATEST,
    VIEWS,
    HIGHEST_PAY,
    NEAREST,
}

// todo: priority ?
sealed interface CareFilter {

    enum class State { SELECTED, UNSELECTED }

    val state: State
}

data class NeighborhoodsFilter(
    val neighborhood: Neighborhood,
    val nearbyNeighborhoodDistance: NearbyNeighborhoodDistance
) : CareFilter {

    override val state: State = State.SELECTED
}

data class CareTypesFilter(
    val careTypes: List<CareType>?
) : CareFilter {

    override val state: State = if (careTypes.isNullOrEmpty()) {
        State.UNSELECTED
    } else {
        State.SELECTED
    }
}

data class PayFilter(val minimum: Int?, val maximum: Int?) : CareFilter {

    override val state: State
        get() = if (minimum == null && maximum == null) {
            State.UNSELECTED
        } else {
            State.SELECTED
        }
}

data class WorkingDaysAndHoursFilter(
    val daysOfWeek: List<DayOfWeek>?,
    val start: LocalTime?,
    val end: LocalTime?
) : CareFilter {

    override val state: State = if (daysOfWeek.isNullOrEmpty() && start == null && end == null) {
        State.UNSELECTED
    } else {
        State.SELECTED
    }
}

data class WorkPeriodFilter(val workPeriod: WorkPeriod?) : CareFilter {

    override val state: State = if (workPeriod == null) {
        State.UNSELECTED
    } else {
        State.SELECTED
    }
}


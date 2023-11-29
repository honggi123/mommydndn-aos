package com.mommydndn.app.ui.features.care

import com.mommydndn.app.domain.model.care.NearbyNeighborhoodDistance
import com.mommydndn.app.domain.model.care.CarePeriod
import com.mommydndn.app.domain.model.care.CareType
import java.time.DayOfWeek
import java.time.LocalTime
import java.time.format.TextStyle
import java.util.Locale

// todo: rename

enum class CareOrder {
    LATEST,
    VIEWS,
    HIGHEST_PAY,
    CLOSEST
}

sealed interface CareFilter {

    val selected: Boolean

    fun displayName(): String
}

data class NeighborhoodsFilter(
    val neighborhoodName: String,
    val nearbyNeighborhoodDistance: NearbyNeighborhoodDistance,
    val nearbyNeighborhoodsCount: Int
) : CareFilter {

    override val selected: Boolean
        get() = true

    override fun displayName(): String = if (nearbyNeighborhoodDistance == NearbyNeighborhoodDistance.IMMEDIATE) {
        neighborhoodName
    } else {
        "$neighborhoodName 외 $nearbyNeighborhoodsCount"
    }
}

data class CareTypesFilter(val careTypes: List<CareType>?) : CareFilter {

    override val selected: Boolean
        get() = !careTypes.isNullOrEmpty()

    override fun displayName(): String = if (careTypes.isNullOrEmpty()) {
        "돌봄종류"
    } else {
        careTypes.sorted().let { careTypes ->
            val postfix = if (careTypes.size > 2) {
                " 외 ${careTypes.size - 2}"
            } else {
                ""
            }

            careTypes.drop(2).joinToString(
                postfix = postfix,
                transform = { it.name }
            )
        }
    }
}

data class PayFilter(val minimum: Int?, val maximum: Int?) : CareFilter {

    init {
        assert(minimum == null || maximum == null || minimum > 9620 || maximum > minimum)
    }

    override val selected: Boolean
        get() = minimum != null && maximum != null

    override fun displayName(): String = if (minimum != null && maximum != null) {
        "시급 ${mWon(minimum)}-${mWon(maximum)}"
    } else {
        "급여"
    }

    private fun mWon(pay: Int) = "${String.format("%.1F", pay / 10_000.0)}만원"
}

data class WorkingHoursFilter(val start: LocalTime?, val end: LocalTime?) : CareFilter {

    init {
        assert(start == null || end == null || end > start)
    }

    override val selected: Boolean
        get() = start != null && end != null

    override fun displayName(): String = if (start != null && end != null) {
        "${start.hour}시-${end.hour}시"
    } else {
        "시간"
    }
}

data class DaysOfWeekFilter(val daysOfWeek: List<DayOfWeek>?) : CareFilter {

    override val selected: Boolean
        get() = !daysOfWeek.isNullOrEmpty()

    override fun displayName(): String = if (daysOfWeek.isNullOrEmpty()) {
        "시간"
    } else {
        daysOfWeek.sorted().let { daysOfWeek ->
            val postfix: String = if (daysOfWeek.size >= 4) {
                " 외 ${daysOfWeek.size - 3}"
            } else {
                ""
            }
            daysOfWeek.drop(3).joinToString(
                separator = "",
                postfix = postfix,
                transform = {
                    it.getDisplayName(TextStyle.NARROW_STANDALONE, Locale.KOREAN)
                }
            )
        }
    }
}

data class CarePeriodFilter(val carePeriod: CarePeriod?) : CareFilter {

    override val selected: Boolean
        get() = carePeriod != null

    override fun displayName(): String = when (carePeriod) {
        CarePeriod.ONE_TIME -> "1회성"
        CarePeriod.REGULAR -> "정기"
        else -> "1회성/정기"
    }
}

data class CareFilters(
    val neighborhoods: NeighborhoodsFilter,
    val careTypes: CareTypesFilter? = null,
    val pay: PayFilter? = null,
    val workingHours: WorkingHoursFilter? = null,
    val daysOfWeek: DaysOfWeekFilter? = null,
    val carePeriod: CarePeriodFilter? = null,
)
package com.mommydndn.app.ui.care.filters

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.mommydndn.app.R
import com.mommydndn.app.domain.model.care.WorkHours
import java.time.LocalTime

data class WorkHoursFilter(
    val startTime: LocalTime? = null,
    val endTime: LocalTime? = null,
) : CareFilter<WorkHours> {

    override val selected: Boolean = startTime != null && endTime != null

    @Composable
    override fun displayName(): String = if (startTime != null && endTime != null) {
        "${startTime.hour}시-${endTime.hour}시"
    } else {
        stringResource(R.string.time)
    }

    override fun predicate(value: WorkHours): Boolean {
        return if (!selected) {
            true
        } else {
            value.start >= startTime && value.end <= endTime
        }
    }
}
package com.mommydndn.app.ui.care.list.filter

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.mommydndn.app.R
import com.mommydndn.app.domain.model.WorkHours

data class WorkHoursFilter(
    val workHours: WorkHours? = null
) : CareFilter {

    override val selected: Boolean
        get() = workHours != null

    @Composable
    override fun displayName(): String {
        return if (workHours != null) {
            with(workHours) {
                stringResource(R.string.hour_hours_between, start.hour, end.hour)
            }
        } else {
            stringResource(R.string.time)
        }
    }
}
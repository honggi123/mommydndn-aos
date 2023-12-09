package com.mommydndn.app.ui.features.care.filters

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.mommydndn.app.R
import com.mommydndn.app.domain.model.care.WorkPeriod

data class WorkPeriodFilter(
    val workPeriod: WorkPeriod? = null
) : CareFilter<WorkPeriod> {

    override val hasValue: Boolean = workPeriod != null

    @Composable
    override fun displayName(): String = when (workPeriod) {
        WorkPeriod.ONE_TIME -> stringResource(R.string.one_time)
        WorkPeriod.REGULAR -> stringResource(R.string.regular)
        else -> stringResource(R.string.one_time_slash_regular)
    }

    override fun predicate(value: WorkPeriod): Boolean {
        TODO("Not yet implemented")
    }
}
package com.mommydndn.app.ui.care.list.filter

import androidx.compose.runtime.Composable
import com.mommydndn.app.ui.care.list.components.WorkPeriodUiModel
import com.mommydndn.app.ui.care.list.components.displayName

data class WorkPeriodFilter(
    private val workPeriod: WorkPeriodUiModel = WorkPeriodUiModel.All
) : CareFilter {

    override val selected: Boolean
        get() = workPeriod != WorkPeriodUiModel.All

    @Composable
    override fun displayName(): String {
        return workPeriod.displayName
    }
}
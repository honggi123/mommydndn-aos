package com.mommydndn.app.ui.models.care

enum class SummaryTabType(val index: Int) {
    JOBOFFER(0),
    JOBSEEKER(1),
    COMPANY(2);

    companion object {
        fun find(index: Int): SummaryTabType? =
            values().find { it.index == index }
    }
}
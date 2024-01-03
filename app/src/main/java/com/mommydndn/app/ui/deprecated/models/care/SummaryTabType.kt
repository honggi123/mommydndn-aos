package com.mommydndn.app.ui.deprecated.models.care

enum class SummaryTabType(val index: Int) {
    JOB_OFFER(0),
    JOB_SEEKER(1),
    COMPANY(2);

    companion object {
        fun find(index: Int): SummaryTabType? =
            values().find { it.index == index }
    }
}

package com.mommydndn.app.ui.care.list.filter

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.mommydndn.app.R

enum class CareOrderBy {
    LATEST, VIEWS, HIGHEST_PAY, NEAREST,
}

val CareOrderBy.displayName: String
    @Composable
    get() = when (this) {
        CareOrderBy.LATEST -> stringResource(R.string.order_by_latest)
        CareOrderBy.VIEWS -> stringResource(R.string.order_by_views)
        CareOrderBy.HIGHEST_PAY -> stringResource(R.string.order_by_highest_pay)
        CareOrderBy.NEAREST -> stringResource(R.string.order_by_nearest)
    }
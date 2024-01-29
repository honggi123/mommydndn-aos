package com.mommydndn.app.ui.care.list.filter

import androidx.compose.runtime.Composable

sealed interface CareFilter {

    val selected: Boolean

    @Composable
    fun displayName(): String
}
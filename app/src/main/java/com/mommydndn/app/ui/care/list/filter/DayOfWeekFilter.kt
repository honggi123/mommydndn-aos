package com.mommydndn.app.ui.care.list.filter

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.mommydndn.app.R
import java.time.DayOfWeek
import java.time.format.TextStyle
import java.util.Locale

data class DayOfWeekFilter(
    private val daysOfWeek: List<DayOfWeek> = DayOfWeek.entries
) : CareFilter {

    override val selected: Boolean = daysOfWeek != DayOfWeek.entries

    @Composable
    override fun displayName(): String {
        if (!selected) {
            return stringResource(R.string.day_of_week)
        }

        return daysOfWeek.sorted().let { daysOfWeek ->
            val names = daysOfWeek.take(3).map {
                it.getDisplayName(TextStyle.NARROW_STANDALONE, Locale.KOREAN)
            }

            val postfix: String = if (daysOfWeek.size > 3) {
                stringResource(id = R.string.other_size, daysOfWeek.size - 3)
            } else {
                ""
            }

            names.joinToString(separator = "", postfix = postfix)
        }
    }
}
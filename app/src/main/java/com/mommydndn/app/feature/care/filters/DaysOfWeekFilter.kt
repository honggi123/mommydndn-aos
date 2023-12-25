package com.mommydndn.app.feature.care.filters

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.mommydndn.app.R
import java.time.DayOfWeek
import java.time.format.TextStyle
import java.util.Locale

data class DaysOfWeekFilter(
    val daysOfWeek: List<DayOfWeek>? = null
) : CareFilter<List<DayOfWeek>> {

    override val selected: Boolean = !daysOfWeek.isNullOrEmpty()

    @Composable
    override fun displayName(): String {
        return if (!selected) {
            stringResource(R.string.day_of_week)
        } else {
            requireNotNull(daysOfWeek)

            daysOfWeek.sorted().let { daysOfWeek ->
                val postfix: String = if (daysOfWeek.size >= 4) {
                    stringResource(id = R.string.other_size, daysOfWeek.size - 3)
                } else {
                    ""
                }

                daysOfWeek.take(3).joinToString(
                    separator = "",
                    postfix = postfix,
                    transform = {
                        it.getDisplayName(TextStyle.NARROW_STANDALONE, Locale.KOREAN)
                    }
                )
            }
        }
    }

    override fun predicate(value: List<DayOfWeek>): Boolean {
        return if (!selected) {
            true
        } else {
            requireNotNull(daysOfWeek).containsAll(value)
        }
    }
}
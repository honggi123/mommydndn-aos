package com.mommydndn.app.ui.care.list.filter

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.mommydndn.app.R
import com.mommydndn.app.domain.model.CareType
import com.mommydndn.app.ui.care.list.components.displayName

data class CareTypeFilter(
    private val careTypes: List<CareType> = CareType.entries
) : CareFilter {

    override val selected: Boolean
        get() = careTypes != CareType.entries

    @Composable
    override fun displayName(): String {
        if (!selected) {
            return stringResource(R.string.care_type)
        }

        return careTypes.sorted().let { careTypes ->
            val names = careTypes.take(2).map { it.displayName }

            val postfix = if (careTypes.size > 2) {
                stringResource(id = R.string.other_size, careTypes.size - 2)
            } else {
                ""
            }

            names.joinToString(postfix = postfix)
        }
    }
}
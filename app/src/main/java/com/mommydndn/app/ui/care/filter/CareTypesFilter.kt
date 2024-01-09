package com.mommydndn.app.ui.care.filter

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.mommydndn.app.R
import com.mommydndn.app.domain.model.care.CareType
import com.mommydndn.app.ui.care.displayName

data class CareTypesFilter(
    val careTypes: List<CareType>? = null
) : CareFilters<List<CareType>> {

    override val selected: Boolean = !careTypes.isNullOrEmpty()

    @Composable
    override fun displayName(): String {
        return if (!selected) {
            return stringResource(R.string.care_type)
        } else {
            requireNotNull(careTypes)

            careTypes.sorted().let { careTypes ->
                val postfix = if (careTypes.size > 2) {
                    stringResource(id = R.string.other_size, careTypes.size - 2)
                } else {
                    ""
                }

                careTypes.take(2)
                    .map { it.displayName() }
                    .joinToString(postfix = postfix)
            }
        }
    }

    override fun predicate(value: List<CareType>): Boolean {
        return if (!selected) {
            true
        } else {
            requireNotNull(careTypes).containsAll(value)
        }
    }
}
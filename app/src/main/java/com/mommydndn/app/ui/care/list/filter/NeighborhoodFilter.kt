package com.mommydndn.app.ui.care.list.filter

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.mommydndn.app.R
import com.mommydndn.app.ui.care.list.NeighborhoodUiModel

class NeighborhoodFilter(
    private val neighborhood: NeighborhoodUiModel
) : CareFilter {

    override val selected: Boolean = true

    @Composable
    override fun displayName(): String {
        return with(neighborhood) {
            val size = nearbyNeighborhoods.getValue(nearbyDistance).size

            val postfix = if (size > 0) {
                stringResource(R.string.other_size, size)
            } else {
                ""
            }

            name + postfix
        }
    }
}
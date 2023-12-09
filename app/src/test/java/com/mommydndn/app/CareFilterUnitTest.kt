package com.mommydndn.app

import com.mommydndn.app.ui.features.care.filters.CareFilter
import com.mommydndn.app.ui.features.care.filters.CareTypesFilter
import org.junit.Assert.assertEquals
import org.junit.Test

class CareFilterUnitTest {

    @Test
    fun updateFiltersEquals_isCorrect() {
        val filters: Map<Class<out CareFilter>, CareFilter> = emptyMap()

        val careTypesFilter: CareFilter = CareTypesFilter(careTypes = emptyList())

        val actual = filters.toMutableMap().apply {
            put(careTypesFilter::class.java, careTypesFilter)
        }

        val expected: Map<Class<out CareFilter>, CareFilter> = mapOf(Pair(careTypesFilter::class.java, careTypesFilter))

        assertEquals(expected, actual)
    }
}
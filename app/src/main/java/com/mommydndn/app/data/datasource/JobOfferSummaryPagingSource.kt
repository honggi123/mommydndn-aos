package com.mommydndn.app.data.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.mommydndn.app.data.api.service.CaringService
import com.mommydndn.app.data.api.service.MapService
import com.mommydndn.app.data.model.JobOfferSummary
import javax.inject.Inject

private const val STARTING_PAGE_INDEX = 1

class JobOfferSummaryPagingSource @Inject constructor(
    private val caringService: CaringService
) : PagingSource<Int, JobOfferSummary>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, JobOfferSummary> {
        return try {
            val position = params.key ?: STARTING_PAGE_INDEX
            val result = caringService.fetchJobOfferSummary(
                keyword = "",
                pageNum = position,
                pageSize = 20,
                sortingCondition = "LATEST",
                emdId = 0,
                neighborhoodScope = "NEARBY_1",
                caringTypeCodeList = listOf("PARENTING"),
                minHourlySalary = 0,
                maxHourlySalary = 0,
                dayTypeCodeList = listOf("MON"),
                startTime = "10:00:00",
                endTime = "24:00:00",
                taskTypeCodeList = listOf("ONETIME")
            )

            val data = result.body() ?: emptyList()

            LoadResult.Page(
                data = data,
                prevKey = when (position) {
                    STARTING_PAGE_INDEX -> null
                    else -> position - 1
                },
                nextKey = if (data.isEmpty()) null else position + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
    override fun getRefreshKey(state: PagingState<Int, JobOfferSummary>): Int? {
        return state.anchorPosition
    }
}
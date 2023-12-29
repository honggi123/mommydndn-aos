package com.mommydndn.app.data.datasource.pagingsource

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.mommydndn.app.data.api.model.request.JobOfferListRequest
import com.mommydndn.app.data.api.model.request.PaginationRequest
import com.mommydndn.app.data.api.service.CareService
import com.mommydndn.app.data.model.care.summary.JobOfferSummaryListItem
import javax.inject.Inject

private const val STARTING_PAGE_INDEX = 1

class JobOfferSummaryPagingSource @Inject constructor(
    private val jobOfferListRequest: JobOfferListRequest,
    private val careService: CareService
) : PagingSource<Int, JobOfferSummaryListItem>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, JobOfferSummaryListItem> {
        return try {
            val position = params.key ?: STARTING_PAGE_INDEX
            val result = careService.fetchJobOfferSummary(
                jobOfferListRequest.copy(
                    paginationRequest = PaginationRequest(
                        pageNum = position,
                        pageSize = 5,
                        requestTimestamp = System.currentTimeMillis()
                    )
                )
            )

            val data = result.body()?.jobOfferSummaryList ?: emptyList()

            LoadResult.Page(
                data = data,
                prevKey = when (position) {
                    STARTING_PAGE_INDEX -> null
                    else -> position - 1
                },
                nextKey = if (data.isEmpty()) null else position + 1
            )
        } catch (e: Exception) {
            Log.e("exception",e.message.toString())
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, JobOfferSummaryListItem>): Int? {
        return state.anchorPosition
    }
}
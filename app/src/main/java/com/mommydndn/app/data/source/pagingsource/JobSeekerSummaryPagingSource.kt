package com.mommydndn.app.data.datasource.pagingsource

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.mommydndn.app.data.model.care.summary.JobSeekerSummaryItem
import com.mommydndn.app.data.network.model.request.JobSeekerListRequest
import com.mommydndn.app.data.network.model.request.PaginationRequest
import com.mommydndn.app.data.network.service.CareService
import javax.inject.Inject

private const val STARTING_PAGE_INDEX = 1

class JobSeekerSummaryPagingSource  @Inject constructor(
    private val jobSeekerListRequest: JobSeekerListRequest,
    private val careService: CareService
) : PagingSource<Int, JobSeekerSummaryItem>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, JobSeekerSummaryItem> {
        return try {
            val position = params.key ?: STARTING_PAGE_INDEX
            val result = careService.fetchJobSeekerSummary(
                jobSeekerListRequest.copy(
                    paginationRequest = PaginationRequest(
                        pageNum = position,
                        pageSize = 5,
                        requestTimestamp = System.currentTimeMillis()
                    )
                )
            )

            val data = result.body()?.jobSeekerSummaryList ?: emptyList()

            LoadResult.Page(
                data = data,
                prevKey = when (position) {
                    STARTING_PAGE_INDEX -> null
                    else -> position - 1
                },
                nextKey = if (data.isEmpty()) null else position + 1
            )
        } catch (e: Exception) {
            Log.e("exception", e.message.toString())
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, JobSeekerSummaryItem>): Int? {
        return state.anchorPosition
    }
}
package com.mommydndn.app.data.source.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.mommydndn.app.data.network.service.CareService
import com.mommydndn.app.data.network.service.request.GetCareJobListRequest
import com.mommydndn.app.data.network.service.care.response.CareJobSummaryApiModel
import com.mommydndn.app.data.network.model.NetworkPage
import javax.inject.Inject

private const val STARTING_PAGE_INDEX = 1
class CareJobSummaryPagingSource @Inject constructor(
    private val getCareJobListRequest: GetCareJobListRequest,
    private val careService: CareService
) : PagingSource<Int, CareJobSummaryApiModel>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CareJobSummaryApiModel> {
        return try {
            val position = params.key ?: STARTING_PAGE_INDEX
            val result = careService.getJobOpeningSummaryList(
                getCareJobListRequest.map {
                    it.copy(
                        pageMeta = NetworkPage(
                            page = position,
                            size = 5,
                            requestedAt = System.currentTimeMillis()
                        )
                    )
                }
            )

            val data = result?.items ?: emptyList()

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

    override fun getRefreshKey(state: PagingState<Int, CareJobSummaryApiModel>): Int? {
        return state.anchorPosition
    }
}
package com.mommydndn.app.data.source.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.mommydndn.app.data.network.service.CareService
import com.mommydndn.app.data.network.service.request.GetCareWorkerListRequest
import com.mommydndn.app.data.network.service.care.response.CareWorkerSummaryApiModel
import com.mommydndn.app.data.network.model.NetworkPage
import javax.inject.Inject

private const val STARTING_PAGE_INDEX = 1

class CareWorkerSummaryPagingSource @Inject constructor(
    private val getCareWorkerListRequest: GetCareWorkerListRequest,
    private val careService: CareService
) : PagingSource<Int, CareWorkerSummaryApiModel>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CareWorkerSummaryApiModel> {
        return try {
            val position = params.key ?: STARTING_PAGE_INDEX
            val result = careService.getCareWorkerSummaryList(
                getCareWorkerListRequest.map {
                    it.copy(
                        pageMeta = NetworkPage(
                            page = position,
                            size = 5,
                            requestedAt = System.currentTimeMillis()
                        )
                    )
                }
            )

            val data = result.items

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

    override fun getRefreshKey(state: PagingState<Int, CareWorkerSummaryApiModel>): Int? {
        return state.anchorPosition
    }
}
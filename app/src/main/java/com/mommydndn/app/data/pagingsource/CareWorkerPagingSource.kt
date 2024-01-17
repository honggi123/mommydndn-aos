package com.mommydndn.app.data.pagingsource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.mommydndn.app.data.network.model.NetworkPage
import com.mommydndn.app.data.network.service.CareService
import com.mommydndn.app.data.network.service.request.GetCareWorkersRequest
import com.mommydndn.app.data.network.service.response.CareWorkerSummaryApiModel
import javax.inject.Inject

class CareWorkerPagingSource @Inject constructor(
    private val request: GetCareWorkersRequest,
    private val service: CareService
) : PagingSource<Int, CareWorkerSummaryApiModel>() {

    companion object {
        private const val INITIAL_PAGE = 1
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CareWorkerSummaryApiModel> {
        return try {
            val page = params.key ?: INITIAL_PAGE

            val result = service.getCareWorkers(
                request.copy(
                    pageMeta = NetworkPage(
                        page = page,
                        size = 5,
                        requestedAt = System.currentTimeMillis()
                    )
                )
            )

            val data = result.items

            LoadResult.Page(
                data = data,
                prevKey = if (page == INITIAL_PAGE) {
                    null
                } else {
                    page - 1
                },
                nextKey = if (data.isEmpty()) {
                    null
                } else {
                    page + 1
                }
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    // TODO
    override fun getRefreshKey(state: PagingState<Int, CareWorkerSummaryApiModel>): Int? {
        return state.anchorPosition
    }
}
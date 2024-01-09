package com.mommydndn.app.data.datasource.pagingsource

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.mommydndn.app.data.network.service.care.CareService
import com.mommydndn.app.data.network.service.care.request.GetCareProviderListRequest
import com.mommydndn.app.data.network.service.care.response.CareProviderSummaryApiModel
import com.mommydndn.app.data.network.service.common.model.PaginationApiModel
import javax.inject.Inject

private const val STARTING_PAGE_INDEX = 1

class CareProviderSummaryPagingSource @Inject constructor(
    private val getCareProviderListRequest: GetCareProviderListRequest,
    private val careService: CareService
) : PagingSource<Int, CareProviderSummaryApiModel>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CareProviderSummaryApiModel> {
        return try {
            val position = params.key ?: STARTING_PAGE_INDEX
            val result = careService.getCareProviderSummaryList(
                getCareProviderListRequest.map {
                    it.copy(
                        pageMeta = PaginationApiModel(
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
            Log.e("exception", e.message.toString())
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, CareProviderSummaryApiModel>): Int? {
        return state.anchorPosition
    }
}
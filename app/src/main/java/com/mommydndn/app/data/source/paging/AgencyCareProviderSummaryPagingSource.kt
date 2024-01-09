package com.mommydndn.app.data.source.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.mommydndn.app.data.network.service.care.CareService
import com.mommydndn.app.data.network.service.care.request.GetAgencyCareProviderListRequest
import com.mommydndn.app.data.network.service.care.response.AgencyCareProviderSummaryApiModel
import com.mommydndn.app.data.network.service.common.model.PaginationApiModel
import javax.inject.Inject

private const val STARTING_PAGE_INDEX = 1

class AgencyCareProviderSummaryPagingSource @Inject constructor(
    private val companyListRequest: GetAgencyCareProviderListRequest,
    private val careService: CareService
) : PagingSource<Int, AgencyCareProviderSummaryApiModel>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, AgencyCareProviderSummaryApiModel> {
        return try {
            val position = params.key ?: STARTING_PAGE_INDEX
            val result = careService.getAgencyCareProviderSummaryList(
                companyListRequest.copy(
                    pageMeta = PaginationApiModel(
                        page = position,
                        size = 5,
                        requestedAt = System.currentTimeMillis()
                    )
                )
            )

            val data = result.items ?: emptyList()

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

    override fun getRefreshKey(state: PagingState<Int, AgencyCareProviderSummaryApiModel>): Int? {
        return state.anchorPosition
    }
}
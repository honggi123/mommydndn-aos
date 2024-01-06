package com.mommydndn.app.data.source.pagingsource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.mommydndn.app.data.network.model.care.response.CompanySummaryListItem
import com.mommydndn.app.data.network.model.care.request.GetAgencyCareProviderListRequest
import com.mommydndn.app.data.network.model.common.PaginationRequest
import com.mommydndn.app.data.network.service.CareService
import javax.inject.Inject

private const val STARTING_PAGE_INDEX = 1
class CompanySummaryPagingSource @Inject constructor(
    private val companyListRequest: GetAgencyCareProviderListRequest,
    private val careService: CareService
) : PagingSource<Int, CompanySummaryListItem>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CompanySummaryListItem> {
        return try {
            val position = params.key ?: STARTING_PAGE_INDEX
            val result = careService.fetchCompanySummary(
                companyListRequest.copy(
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
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, CompanySummaryListItem>): Int? {
        return state.anchorPosition
    }
}
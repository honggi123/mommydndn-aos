package com.mommydndn.app.data.pagingsource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.mommydndn.app.data.network.model.NetworkNeighborhood
import com.mommydndn.app.data.network.service.LocationService
import javax.inject.Inject

class NeighborhoodSearchResultPagingSource @Inject constructor(
    private val query: String,
    private val service: LocationService
) : PagingSource<Int, NetworkNeighborhood>() {

    companion object {
        private const val INITIAL_PAGE = 1
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, NetworkNeighborhood> {
        return try {
            val page = params.key ?: INITIAL_PAGE

            val result = service.searchLocationByKeyword(
                query,
                skip = (page - 1) * params.loadSize,
                size = params.loadSize,
                requestedAt = System.currentTimeMillis()
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
    override fun getRefreshKey(state: PagingState<Int, NetworkNeighborhood>): Int? {
        return state.anchorPosition
    }
}
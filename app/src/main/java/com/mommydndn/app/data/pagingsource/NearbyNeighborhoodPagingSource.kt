package com.mommydndn.app.data.pagingsource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.mommydndn.app.data.network.model.NetworkNeighborhood
import com.mommydndn.app.data.network.service.LocationService
import com.mommydndn.app.domain.model.Coordinates
import javax.inject.Inject

class NearbyNeighborhoodPagingSource @Inject constructor(
    private val coordinates: Coordinates,
    private val locationService: LocationService
) : PagingSource<Int, NetworkNeighborhood>() {

    companion object {
        private const val START_PAGE = 1
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, NetworkNeighborhood> {
        return try {
            val page = params.key ?: START_PAGE

            val result = locationService.getNearestNeighborhoods(
                latitude = coordinates.latitude,
                longitude = coordinates.longitude,
                skip = (page - 1) * params.loadSize,
                limit = params.loadSize
            )

            val data = result.items

            LoadResult.Page(
                data = data,
                prevKey = if (page == START_PAGE) {
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
    override fun getRefreshKey(state: PagingState<Int, NetworkNeighborhood>): Int? {
        return state.anchorPosition
    }
}
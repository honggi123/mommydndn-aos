package com.mommydndn.app.data.source.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.mommydndn.app.data.network.model.NetworkNeighborhood
import com.mommydndn.app.data.network.service.LocationService
import com.mommydndn.app.domain.model.location.CoordinatesInfo
import javax.inject.Inject

private const val STARTING_PAGE_INDEX = 1

class NearbyLocationsPagingSource @Inject constructor(
    private val coordinatesInfo: CoordinatesInfo,
    private val locationService: LocationService
) : PagingSource<Int, NetworkNeighborhood>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, NetworkNeighborhood> {
        return try {
            val position = params.key ?: STARTING_PAGE_INDEX
            val result =
                locationService.getNearestNeighborhoods(
                    latitude = coordinatesInfo.latitude,
                    longitude = coordinatesInfo.longitude,
                    skip = (position - 1) * params.loadSize,
                    limit = params.loadSize
                )
            result.items
            val data = result.items?.map { it } ?: emptyList()

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

    override fun getRefreshKey(state: PagingState<Int, NetworkNeighborhood>): Int? {
        return state.anchorPosition
    }
}
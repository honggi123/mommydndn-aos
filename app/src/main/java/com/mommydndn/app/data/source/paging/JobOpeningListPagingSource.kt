package com.mommydndn.app.data.source.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.mommydndn.app.data.network.model.care.GetCareJobOpeningListItem
import com.mommydndn.app.data.network.model.care.GetCareJobOpeningListRequest
import com.mommydndn.app.data.network.service.CareService
import com.mommydndn.app.domain.model.care.CareJobOpening
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class JobOpeningListPagingSource constructor(
    private val request: GetCareJobOpeningListRequest,
    private val careService: CareService,
) : PagingSource<Int, CareJobOpening>() {

    override fun getRefreshKey(state: PagingState<Int, CareJobOpening>): Int? {
        TODO("Not yet implemented")
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CareJobOpening> {
        return try {
            val page = params.key ?: 1

            val response = withContext(Dispatchers.IO) {
                careService.getCareJobOpeningList(request)
            }

            LoadResult.Page(
                data = response.items.map(::toEntity),
                prevKey = if (page == 1) {
                    null
                } else {
                    page - 1
                },
                nextKey = if (response.items.isEmpty()) {
                    null
                } else {
                    page + 1
                }
            )
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }
}

fun toEntity(item: GetCareJobOpeningListItem): CareJobOpening = TODO()

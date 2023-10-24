package com.mommydndn.app.data.datasource.pagingsource

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.mommydndn.app.data.api.model.response.AddressDocument
import com.mommydndn.app.data.api.service.KakaoApiService
import com.mommydndn.app.data.api.service.MapService
import com.mommydndn.app.data.model.map.EmdItem
import javax.inject.Inject

private const val STARTING_PAGE_INDEX = 1

class AddressByKeyWordPagingSource @Inject constructor(
    private val keyWord: String,
    private val kakaoApiService: KakaoApiService
) : PagingSource<Int, AddressDocument>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, AddressDocument> {
        return try {
            val position = params.key ?: STARTING_PAGE_INDEX
            val result =
                kakaoApiService.fetchAddressInfo(
                    query = keyWord,
                    page = 1,
                    size = 15,
                )
            Log.e("result",result.body().toString())
            val data = result.body()?.documents ?: emptyList()

            LoadResult.Page(
                data = data,
                prevKey = when (position) {
                    STARTING_PAGE_INDEX -> null
                    else -> position - 1
                },
                nextKey = if (data.isEmpty()) null else position + 1
            )
        } catch (e: Exception) {
            Log.e("Exception",e.toString())

            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, AddressDocument>): Int? {
        return state.anchorPosition
    }
}
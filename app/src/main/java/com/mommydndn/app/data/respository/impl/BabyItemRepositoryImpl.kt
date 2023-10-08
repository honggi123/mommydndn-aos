package com.mommydndn.app.data.respository.impl

import android.util.Log
import com.mommydndn.app.data.api.service.BabyItemService
import com.mommydndn.app.data.model.BabyItem
import com.mommydndn.app.data.respository.BabyItemRepository
import com.skydoves.sandwich.onError
import com.skydoves.sandwich.onException
import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class BabyItemRepositoryImpl @Inject constructor(
    private val babyItemService: BabyItemService
) : BabyItemRepository {
    override fun fetchNearestBabyItem(): Flow<List<BabyItem>> = flow {
        babyItemService.fetchNearestBabyItem(
            pageSize = 6,
            pageNum = 1
        ).suspendOnSuccess {
            val list = data.itemSummaryList.map {
                BabyItem(
                    createdAt = it.createdAt.toString(),
                    imageUrl = it.imageUrl,
                    itemId = it.itemId,
                    neighborhood = it.neighborhood,
                    price = it.price,
                    title = it.title
                )
            }
            emit(list)
        }.onError {

        }.onException {

        }
    }
}
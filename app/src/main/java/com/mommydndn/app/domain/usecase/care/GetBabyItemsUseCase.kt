package com.mommydndn.app.domain.usecase.care

import com.mommydndn.app.data.model.babyitem.BabyItemSummary
import com.mommydndn.app.domain.repository.BabyItemRepository
import com.mommydndn.app.domain.usecase.FlowUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetBabyItemsUseCase @Inject constructor(
    private val repository: BabyItemRepository,
) : FlowUseCase<GetBabyItemsParams, BabyItemSummary>(Dispatchers.IO) {

    override fun execute(parameters: GetBabyItemsParams): Flow<BabyItemSummary> {
        return parameters.let {
            repository.fetchNearestBabyItemSummary(
                pageNum = it.pageNum,
                pageSize = it.pageSize,
                currentTimestamp = it.currentTimeStamp
            )
        }
    }

}

data class GetBabyItemsParams(
    val pageNum: Int,
    val pageSize: Int,
    val currentTimeStamp: Long
)

package com.mommydndn.app.domain.usecase.care

import com.mommydndn.app.domain.model.care.BabyItemsWithMeta
import com.mommydndn.app.domain.repository.BabyItemRepository
import com.mommydndn.app.domain.usecase.FlowUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetBabyItemsUseCase @Inject constructor(
    private val repository: BabyItemRepository,
    private val coroutineDispatcher: CoroutineDispatcher
) : FlowUseCase<GetBabyItemsParams, BabyItemsWithMeta>(coroutineDispatcher) {

    override fun execute(parameters: GetBabyItemsParams): Flow<BabyItemsWithMeta> {
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
